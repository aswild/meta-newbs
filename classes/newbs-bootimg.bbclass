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

IMAGE_BOOTLOADER ?= "bcm2835-bootfiles"

KERNEL_NAME = "kernel7.img"
KERNEL_NAME_raspberrypi3-64 = "kernel8.img"


# 32 MB default boot partition (in 1K blocks)
DEFAULT_BOOTIMG_SIZE = "32768"
BOOTIMG_SIZE ?= "${DEFAULT_BOOTIMG_SIZE}"
BOOTIMG_LABEL ?= "NEWBS"

do_image_newbs_bootimg[depends] += " \
    mtools-native:do_populate_sysroot \
    dosfstools-native:do_populate_sysroot \
    xz-native:do_populate_sysroot \
    virtual/kernel:do_deploy \
    ${IMAGE_BOOTLOADER}:do_deploy \
    rpi-config:do_deploy \
    ${@bb.utils.contains('RPI_USE_U_BOOT', '1', 'u-boot:do_deploy', '',d)} \
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
    install -t $BOOT_DIR ${DEPLOY_DIR_IMAGE}/${IMAGE_BOOTLOADER}/*
    rm -vf ${DEPLOY_DIR_IMAGE}/${IMAGE_BOOTLOADER}/*.stamp

    # copy device tree files
    DTS="${KERNEL_DEVICETREE}"
    if [ -n "$DTS" ]; then
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

    rm -f ${DEPLOY_BOOTIMG}
    mkfs.vfat -n ${BOOTIMG_LABEL} -S 512 -C ${DEPLOY_BOOTIMG} ${BOOTIMG_SIZE}

    [ -n "${DTS}" ] && mmd -i ${DEPLOY_BOOTIMG} overlays
    find $BOOT_DIR -type f | grep -v '.stamp$' | while read file; do
        mcopy -v -i ${DEPLOY_BOOTIMG} -s $file ::/$(echo $file | sed "s|${BOOT_DIR}||")
    done

    tar cvf - -C $BOOT_DIR . | xz -z -c --threads=0 >${DEPLOY_BOOTTAR}

    ln -sfv $(basename ${DEPLOY_BOOTIMG}) ${DEPLOY_BOOTIMG_SYMLINK}
    ln -sfv $(basename ${DEPLOY_BOOTTAR}) ${DEPLOY_BOOTTAR_SYMLINK}
}
