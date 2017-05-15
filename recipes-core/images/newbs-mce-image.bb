SUMMARY = "NEWBS Media Center Edition Image"
LICENSE = "MIT"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    kernel-modules \
    packagegroup-newbs-core \
    packagegroup-newbs-utils \
    packagegroup-kodi \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

IMAGE_INSTALL_append_raspberrypi3 = " packagegroup-rpi3-wifi"

inherit newbs-image
