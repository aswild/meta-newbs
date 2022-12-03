DESCRIPTION = "NEWBS Recovery Root FS packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES += "${PN}-core"
RDEPENDS:${PN}-core = " \
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
RDEPENDS:${PN}-full-cmdline = " \
    ${PN}-core \
    ${PN}-zfs \
    packagegroup-wild-utils \
    packagegroup-wild-network-utils \
    borgbackup \
    exfat-utils \
    fuse-exfat \
    i2c-tools \
    minicom \
    squashfs-tools \
"

PACKAGES += "${PN}-zfs"
RDEPENDS:${PN}-zfs = " \
    zfs-module \
    zfs-tools \
    zfs-newbs \
"
