# Create a vfat partition image which can be dd'd to an SD card
# This class is based on meta-raspberrypi/classes/sdcard_image-rpi.bbclass
# but doesn't create a partition image for the root filesystem

inherit image_types
inherit linux-raspberrypi-base

KERNEL_INITRAMFS ?= ""
IMAGE_BOOTLOADER ?= "bcm2835-bootfiles"

# strip custom linux version extension to avoid breaking linux-raspberrypi-base
python __anonymous() {
    import re
    staging_dir = d.getVar("STAGING_KERNEL_BUILDDIR", True)
    ver = get_kernelversion_file(staging_dir)
    ver = re.sub(r"-.*$", "", ver)

    d.setVar("KERNEL_VERSION_BASE", ver)
}

KERNEL_NAME = "kernel7.img"
NEWBS_INIT_DEST ?= "newbs-init.${INITRAMFS_FSTYPES}"

# we have to guess the symlink that gets deployed for init
INIT_DEPLOY_SYMLINK ?= "${NEWBS_INIT}-${MACHINE}.${INITRAMFS_FSTYPES}"


# 32 MB default boot partition (in 1K blocks)
BOOTIMG_SIZE ?= "20480"
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
    install -d $BOOT_DIR
#    install -m 644 ${IMGDEPLOYDIR}/${KERNEL_IMAGETYPE}${KERNEL_INITRAMFS}-${MACHINE}.bin \
#                   $BOOT_DIR/${KERNEL_NAME}
    install -t $BOOT_DIR ${DEPLOY_DIR_IMAGE}/${IMAGE_BOOTLOADER}/*
    #install $(readlink -f "${IMGDEPLOYDIR}/${INIT_DEPLOY_SYMLINK}") $BOOT_DIR/${NEWBS_INIT_DEST}

    DTS="${@get_dts(d, d.getVar('KERNEL_VERSION_BASE',True))}"
    if [ -n "$DTS" ]; then
        DT_OVERLAYS="${@split_overlays(d, 0, d.getVar('KERNEL_VERSION_BASE',True))}"
        DT_ROOT="${@split_overlays(d, 1, d.getVar('KERNEL_VERSION_BASE',True))}"

        for DTB in $DT_ROOT; do
            dtbname=${KERNEL_IMAGETYPE}-$(basename $DTB)
            install -m 644 ${DEPLOY_DIR_IMAGE}/${DTB_DEPLOYDIR}/$dtbname $BOOT_DIR/
        done

        install -d $BOOT_DIR/overlays
        for DTB in ${DT_OVERLAYS}; do
            dtbname=${KERNEL_IMAGETYPE}-$(basename $DTB)
            install -m 644 ${DEPLOY_DIR_IMAGE}/${DTB_DEPLOYDIR}/$dtbname $BOOT_DIR/overlays/
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
    tar cvf - -C $BOOT_DIR . | xz -z -c --threads=0 >${DEPLOY_TARBALL}

    ln -sfv $(basename ${DEPLOY_BOOTIMG}) ${DEPLOY_BOOTIMG_SYMLINK}
    ln -sfv $(basename ${DEPLOY_TARBALL}) ${DEPLOY_TARBALL_SYMLINK}
}
