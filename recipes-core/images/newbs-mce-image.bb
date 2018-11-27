SUMMARY = "NEWBS Media Center Edition Image"
LICENSE = "MIT"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    kernel-modules \
    packagegroup-newbs-core \
    packagegroup-wild-utils \
    packagegroup-kodi \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

inherit newbs-image
