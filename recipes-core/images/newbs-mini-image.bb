SUMMARY = "NEWBS core recovery image"
LICENSE = "MIT"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    packagegroup-wild-base \
    kernel-modules \
    newbs-nvram \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

inherit newbs-image
