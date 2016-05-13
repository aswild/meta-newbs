SUMMARY = "NEWBS core recovery image"
LICENSE = "MIT"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    packagegroup-newbs-recovery \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

inherit core-image
