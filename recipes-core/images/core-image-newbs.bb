SUMMARY = "NEWBS core recovery image"
LICENSE = "MIT"

DEPENDS = "${NEWBS_INIT}"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    kernel-modules \
    packagegroup-newbs-recovery \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

#${@bb.utils.contains('IMAGE_FEATURES', 'rpi3-wifi', 'packagegroup-rpi3-wifi', ''}

IMAGE_INSTALL_append_rpi3-wifi = "packagegroup-rpi3-wifi"

# Yocto will install the kernel image to /boot, but we don't want that because
# the boot partition will be mounted in /boot (by fstab in base-files)
clear_boot_dir() {
    cd ${IMAGE_ROOTFS}
    rm -vf boot/*
}
ROOTFS_POSTPROCESS_COMMAND += "clear_boot_dir; "

inherit core-image

# disable sstate-cache control of images
# openembedded-core commit d54339d4b1a7e884de636f6325ca60409ebd95ff makes it so
# old images are always deleted, which is lame
#do_rootfs[nostamp] = "1"
#do_image[nostamp] = "1"
#SSTATETASKS_remove = "do_image_complete"
#IMGDEPLOYDIR = "${DEPLOY_DIR_IMAGE}"
