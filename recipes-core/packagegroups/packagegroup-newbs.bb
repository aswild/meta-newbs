DESCRIPTION = "NEWBS Recovery Root FS packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES += "${PN}-core"
RDEPENDS_${PN}-core = " \
    packagegroup-base \
    packagegroup-wild-core \
    newbs-lastboot-timestamp \
    newbs-nvram \
    newbs-swdl \
    udev-extraconf \
    userland \
    ${@bb.utils.contains('KERNEL_FEATURES', 'neostrip', 'linux-newbs-headers-dev neostrip-demo', '', d)} \
"

PACKAGES += "${PN}-full-cmdline"
RDEPENDS_${PN}-full-cmdline = " \
    ${PN}-core \
    packagegroup-wild-utils \
    packagegroup-wild-network-utils \
    exfat-utils \
    fuse-exfat \
    i2c-tools \
    minicom \
    squashfs-tools \
"
