SUMMARY = "NEWBS base image"
LICENSE = "MIT"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    kernel-modules \
    packagegroup-newbs-full-cmdline \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

inherit newbs-image
