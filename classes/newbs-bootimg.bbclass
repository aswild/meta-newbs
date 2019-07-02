# Create a vfat partition image which can be dd'd to an SD card
# This class is based on meta-raspberrypi/classes/sdcard_image-rpi.bbclass
# but doesn't create a partition image for the root filesystem

inherit image_types

def kernel_initramfs_extension(d):
    image = d.getVar('INITRAMFS_IMAGE')
    bundle = d.getVar('INITRAMFS_IMAGE_BUNDLE')
    if image and bundle == '1':
        return '-initramfs'
    return ''

# from meta-raspberrypi's sdcard_image-rpi.bbclass
def split_overlays(d, out):
    dts = d.getVar("KERNEL_DEVICETREE")
    if out:
        overlays = oe.utils.str_filter_out('\S+\-overlay\.dtb$', dts, d)
        overlays = oe.utils.str_filter_out('\S+\.dtbo$', overlays, d)
    else:
        overlays = oe.utils.str_filter('\S+\-overlay\.dtb$', dts, d) + \
                   " " + oe.utils.str_filter('\S+\.dtbo$', dts, d)

    return overlays

def get_initramfs_file(d):
    initramfs_name = d.getVar('BOOTIMG_INITRAMFS')
    if initramfs_name:
        dd = d.createCopy()
        dd.setVar('IMAGE_BASENAME', initramfs_name)
        initramfs_link_name = dd.getVar('IMAGE_LINK_NAME') + '.cpio.gz'
        return os.path.join(d.getVar('DEPLOY_DIR_IMAGE'), initramfs_link_name)
    return ''

def get_initramfs_dependency(d):
    initramfs_name = d.getVar('BOOTIMG_INITRAMFS')
    if initramfs_name:
        return initramfs_name + ':do_deploy'
    return ''

def volume_id(d):
    """ construct a volume id (32-bit hex number) from the image name """
    from hashlib import sha1
    h = sha1()
    h.update(d.getVar('IMAGE_NAME').encode('UTF-8'))
    return h.hexdigest().upper()[:8]

IMAGE_BOOTLOADER ?= "bcm2835-bootfiles"

BOOTIMG_INITRAMFS ?= ""
BOOTIMG_INITRAMFS_FILE = "${@get_initramfs_file(d)}"

KERNEL_NAME = "kernel7.img"
KERNEL_NAME_aarch64 = "kernel8.img"

# 48 MB default boot partition (in 1K blocks)
DEFAULT_BOOTIMG_SIZE = "49152"
BOOTIMG_SIZE ?= "${DEFAULT_BOOTIMG_SIZE}"
BOOTIMG_ID = "${@volume_id(d)}"
BOOTIMG_LABEL = "NEWBS-${@volume_id(d)[4:]}"

do_image_newbs_bootimg[depends] += " \
    mtools-native:do_populate_sysroot \
    dosfstools-native:do_populate_sysroot \
    xz-native:do_populate_sysroot \
    virtual/kernel:do_deploy \
    ${IMAGE_BOOTLOADER}:do_deploy \
    rpi-config:do_deploy \
    ${@bb.utils.contains('RPI_USE_U_BOOT', '1', 'u-boot:do_deploy', '',d)} \
    ${@get_initramfs_dependency(d)} \
"

DEPLOY_BOOTIMG_NAME    = "${IMAGE_NAME}.boot.vfat"
DEPLOY_BOOTIMG         = "${IMGDEPLOYDIR}/${DEPLOY_BOOTIMG_NAME}"
DEPLOY_BOOTIMG_SYMLINK = "${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.boot.vfat"

DEPLOY_BOOTTAR_NAME    = "${IMAGE_NAME}.boot.tar.xz"
DEPLOY_BOOTTAR         = "${IMGDEPLOYDIR}/${DEPLOY_BOOTTAR_NAME}"
DEPLOY_BOOTTAR_SYMLINK = "${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.boot.tar.xz"

IMAGE_CMD_newbs-bootimg() {
    BOOT_DIR=${WORKDIR}/boot
    rm -rf ${BOOT_DIR}
    install -d $BOOT_DIR

    # copy bootloader files
    cp -av ${DEPLOY_DIR_IMAGE}/${IMAGE_BOOTLOADER}/* $BOOT_DIR
    rm -vf $BOOT_DIR/*.stamp

    # copy device tree files
    if [ -n "${KERNEL_DEVICETREE}" ]; then
        DT_OVERLAYS="${@split_overlays(d, 0)}"
        DT_ROOT="${@split_overlays(d, 1)}"

        for DTB in $DT_ROOT; do
            dtbname=$(basename $DTB)
            install -m 644 ${DEPLOY_DIR_IMAGE}/${DTB_DEPLOYDIR}/$dtbname $BOOT_DIR/$dtbname
        done

        install -d $BOOT_DIR/overlays
        for DTB in ${DT_OVERLAYS}; do
            dtbname=$(basename $DTB)
            install -m 644 ${DEPLOY_DIR_IMAGE}/${DTB_DEPLOYDIR}/$dtbname $BOOT_DIR/overlays/$dtbname
        done
    fi

    # copy U-boot files and kernel
    if [ "${RPI_USE_U_BOOT}" = "1" ]; then
        # install u-boot as kernel*.img
        install -m 644 ${DEPLOY_DIR_IMAGE}/u-boot.bin $BOOT_DIR/${KERNEL_NAME}
        # install kernel as *Image
        install -m 644 ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}${@kernel_initramfs_extension(d)}-${MACHINE}.bin \
                       $BOOT_DIR/${KERNEL_IMAGETYPE}
        # install boot script
        install -m 644 ${DEPLOY_DIR_IMAGE}/boot.scr $BOOT_DIR/boot.scr
    else
        # install kernel as kernel*.img
        install -m 644 ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}${@kernel_initramfs_extension(d)}-${MACHINE}.bin \
                       $BOOT_DIR/${KERNEL_NAME}
    fi

    # Image name stamp file
    echo "${IMAGE_NAME}" >$BOOT_DIR/version

    # copy initramfs and append to config.txt
    if [ -n "${BOOTIMG_INITRAMFS}" ]; then
        if [ ! -f "${BOOTIMG_INITRAMFS_FILE}" ]; then
            bbfatal "BOOTIMG_INITRAMFS is set (${BOOTIMG_INITRAMFS}) but '${BOOTIMG_INITRAMFS_FILE}' doesn't exist"
        fi

        bbnote "Installing ${BOOTIMG_INITRAMFS_FILE} as initramfs"
        install -m644 ${BOOTIMG_INITRAMFS_FILE} $BOOT_DIR/initramfs.cpio.gz
        echo "initramfs initramfs.cpio.gz followkernel" >>$BOOT_DIR/config.txt
    fi

    rm -f ${DEPLOY_BOOTIMG}
    mkfs.vfat -n ${BOOTIMG_LABEL} -i ${BOOTIMG_ID} -S 512 -C ${DEPLOY_BOOTIMG} ${BOOTIMG_SIZE}
    mcopy -i ${DEPLOY_BOOTIMG} -s $BOOT_DIR/* ::/

    tar cvf - -C $(dirname $BOOT_DIR) $(basename $BOOT_DIR) | xz -z -c --threads=0 >${DEPLOY_BOOTTAR}

    ln -sfv $(basename ${DEPLOY_BOOTIMG}) ${DEPLOY_BOOTIMG_SYMLINK}
    ln -sfv $(basename ${DEPLOY_BOOTTAR}) ${DEPLOY_BOOTTAR_SYMLINK}
}
