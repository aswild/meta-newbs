SUMMARY = "NEWBS core recovery image"
LICENSE = "MIT"

inherit image

RDEPENDS = " \
    packagegroup-core-ssh-dropbear \
"

IMAGE_INSTALL = " \
    packagegroup-core-ssh-dropbear \
"
