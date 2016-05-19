# Create a vfat partition image which can be dd'd to an SD card
# This class is based on meta-raspberrypi/classes/sdcard_image-rpi.bbclass
# but doesn't create a partition image for the root filesystem

inherit image_types
inherit linux-raspberrypi-base

KERNEL_INITRAMFS ?= ""
IMAGE_BOOTLOADER ?= "bcm2835-bootfiles"

# needs to be in sync with recipes-bsp/bootfiles/newbs-config.patch
#KERNEL_NAME = "kernel-newbs.img"
KERNEL_NAME = "kernel7.img"

# 100 MB default boot partition (in 1K blocks)
BOOTIMG_SIZE ?= "102400"
BOOTIMG_LABEL ?= "NEWBS"

IMAGE_DEPENDS_newbs-bootimg = " \
    mtools-native \
    dosfstools-native \
    virtual/kernel:do_package \
    ${IMAGE_BOOTLOADER} \
"

DEPLOY_BOOTIMG_NAME    = "${IMAGE_NAME}.boot.vfat"
DEPLOY_BOOTIMG         = "${DEPLOY_DIR_IMAGE}/${DEPLOY_BOOTIMG_NAME}"
DEPLOY_BOOTIMG_SYMLINK = "${DEPLOY_DIR_IMAGE}/${IMAGE_BASENAME}-${MACHINE}.boot.vfat"

IMAGE_CMD_newbs-bootimg() {
    BOOT_DIR=${WORKDIR}/boot
    install -d $BOOT_DIR
    install ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}${KERNEL_INITRAMFS}-${MACHINE}.bin $BOOT_DIR/${KERNEL_NAME}
    install -t $BOOT_DIR ${DEPLOY_DIR_IMAGE}/${IMAGE_BOOTLOADER}/*

    DTS="${@get_dts(d, None)}"
    if [ -n "${DTS}" ]; then
        DT_OVERLAYS="${@split_overlays(d, 0)}"
        DT_ROOT="${@split_overlays(d, 1)}"

        for DTB in $DT_ROOT; do
            BASENAME=$(basename $DTB .dtb)
            install ${DEPLOY_DIR_IMAGE}/${DTB_DEPLOYDIR}/${KERNEL_IMAGETYPE}-$BASENAME.dtb $BOOT_DIR/$BASENAME.dtb
        done

        install -d $BOOT_DIR/overlays
        for DTB in ${DT_OVERLAYS}; do
            BASENAME=$(basename $DTB .dtb)
            install ${DEPLOY_DIR_IMAGE}/${DTB_DEPLOYDIR}/${KERNEL_IMAGETYPE}-$BASENAME.dtb $BOOT_DIR/overlays/$BASENAME.dtb
        done
    fi

    rm -f ${DEPLOY_BOOTIMG}
    mkfs.vfat -n ${BOOTIMG_LABEL} -S 512 -C ${DEPLOY_BOOTIMG} ${BOOTIMG_SIZE}

    [ -n "${DTS}" ] && mmd -i ${DEPLOY_BOOTIMG} overlays
    find $BOOT_DIR -type f | grep -v '.stamp$' | while read file; do
        mcopy -v -i ${DEPLOY_BOOTIMG} -s $file ::/$(echo $file | sed "s|${BOOT_DIR}||")
    done

    sync

    DEPLOY_TARBALL=$(echo ${DEPLOY_BOOTIMG} | sed 's|\.boot\.vfat|.boot.tar.xz|')
    DEPLOY_TARBALL_SYMLINK=$(echo ${DEPLOY_BOOTIMG_SYMLINK} | sed 's|\.boot\.vfat|.boot.tar.xz|')
    tar cvJf $DEPLOY_TARBALL -C $BOOT_DIR $(ls $BOOT_DIR)

    ln -sf $(basename ${DEPLOY_BOOTIMG}) ${DEPLOY_BOOTIMG_SYMLINK}
    ln -sf $(basename ${DEPLOY_TARBALL}) ${DEPLOY_TARBALL_SYMLINK}
}
