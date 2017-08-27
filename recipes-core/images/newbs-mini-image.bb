SUMMARY = "NEWBS core recovery image"
LICENSE = "MIT"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    packagegroup-wild-base \
    kernel-modules \
    newbs-nvram \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

IMAGE_INSTALL_append_raspberrypi3 = " packagegroup-rpi3-wifi"

inherit newbs-image
