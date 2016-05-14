SUMMARY = "NEWBS core recovery image"
LICENSE = "MIT"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    packagegroup-newbs-recovery \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

IMAGE_FSTYPES = "ext4 squashfs-xz tar.xz newbs-bootimg"

inherit core-image
