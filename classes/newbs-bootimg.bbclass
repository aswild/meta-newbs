# Create a vfat partition image which can be dd'd to an SD card
# This class is based on meta-raspberrypi/classes/sdcard_image-rpi.bbclass
# but doesn't create a partition image for the root filesystem

inherit image_types
inherit linux-raspberrypi-base

KERNEL_INITRAMFS = "${@bb.utils.contains('INITRAMFS_IMAGE_BUNDLE', '1', '-initramfs', '', d)}"
IMAGE_BOOTLOADER ?= "bcm2835-bootfiles"

# strip custom linux version extension to avoid breaking linux-raspberrypi-base
#python __anonymous() {
#    import re
#    staging_dir = d.getVar("STAGING_KERNEL_BUILDDIR")
#    ver = get_kernelversion_file(staging_dir)
#    ver = re.sub(r"-.*$", "", ver)
#
#    d.setVar("KERNEL_VERSION_BASE", ver)
#}

KERNEL_NAME = "kernel7.img"


# 32 MB default boot partition (in 1K blocks)
BOOTIMG_SIZE ?= "32768"
BOOTIMG_LABEL ?= "NEWBS"

IMAGE_DEPENDS_newbs-bootimg = " \
    mtools-native \
    dosfstools-native \
    xz-native \
    virtual/kernel:do_deploy \
    ${IMAGE_BOOTLOADER} \
"

DEPLOY_BOOTIMG_NAME    = "${IMAGE_NAME}.boot.vfat"
DEPLOY_BOOTIMG         = "${IMGDEPLOYDIR}/${DEPLOY_BOOTIMG_NAME}"
DEPLOY_BOOTIMG_SYMLINK = "${IMGDEPLOYDIR}/${IMAGE_BASENAME}-${MACHINE}.boot.vfat"

IMAGE_CMD_newbs-bootimg() {
    BOOT_DIR=${WORKDIR}/boot
    rm -rf ${BOOT_DIR}
    install -d $BOOT_DIR
    install -m 644 ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}${KERNEL_INITRAMFS}-${MACHINE}.bin \
                   $BOOT_DIR/${KERNEL_NAME}

    # copy bootloader files
    install -t $BOOT_DIR ${DEPLOY_DIR_IMAGE}/${IMAGE_BOOTLOADER}/*
    rm -vf ${DEPLOY_DIR_IMAGE}/${IMAGE_BOOTLOADER}/*.stamp

    # copy device tree files
    DTS="${@get_dts(d, d.getVar('KERNEL_VERSION_BASE'))}"
    if [ -n "$DTS" ]; then
        DT_OVERLAYS="${@split_overlays(d, 0, d.getVar('KERNEL_VERSION_BASE'))}"
        DT_ROOT="${@split_overlays(d, 1, d.getVar('KERNEL_VERSION_BASE'))}"

        for DTB in $DT_ROOT; do
            dtb_basename=$(basename $DTB)
            dtbname=${KERNEL_IMAGETYPE}-$dtb_basename
            install -m 644 ${DEPLOY_DIR_IMAGE}/${DTB_DEPLOYDIR}/$dtbname $BOOT_DIR/$dtb_basename
        done

        install -d $BOOT_DIR/overlays
        for DTB in ${DT_OVERLAYS}; do
            dtb_basename=$(basename $DTB)
            dtbname=${KERNEL_IMAGETYPE}-$dtb_basename
            install -m 644 ${DEPLOY_DIR_IMAGE}/${DTB_DEPLOYDIR}/$dtbname $BOOT_DIR/overlays/$dtb_basename
        done
    fi

    rm -f ${DEPLOY_BOOTIMG}
    mkfs.vfat -n ${BOOTIMG_LABEL} -S 512 -C ${DEPLOY_BOOTIMG} ${BOOTIMG_SIZE}

    [ -n "${DTS}" ] && mmd -i ${DEPLOY_BOOTIMG} overlays
    find $BOOT_DIR -type f | grep -v '.stamp$' | while read file; do
        mcopy -v -i ${DEPLOY_BOOTIMG} -s $file ::/$(echo $file | sed "s|${BOOT_DIR}||")
    done

    DEPLOY_TARBALL=$(echo ${DEPLOY_BOOTIMG} | sed 's|\.boot\.vfat|.boot.tar.xz|')
    DEPLOY_TARBALL_SYMLINK=$(echo ${DEPLOY_BOOTIMG_SYMLINK} | sed 's|\.boot\.vfat|.boot.tar.xz|')
    tar cvf - -C $BOOT_DIR . | xz -z -c --threads=0 >${DEPLOY_TARBALL}

    ln -sfv $(basename ${DEPLOY_BOOTIMG}) ${DEPLOY_BOOTIMG_SYMLINK}
    ln -sfv $(basename ${DEPLOY_TARBALL}) ${DEPLOY_TARBALL_SYMLINK}
}
