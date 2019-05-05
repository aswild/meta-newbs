DESCRIPTION = "NEWBS Recovery Root FS packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES += "${PN}-core"
RDEPENDS_${PN}-core = " \
    packagegroup-base \
    packagegroup-wild-core \
    newbs-nvram \
    newbs-init-util \
    newbs-swdl \
    udev-extraconf \
    userland \
    var-log-mount \
    ${@bb.utils.contains('KERNEL_FEATURES', 'neostrip', 'linux-newbs-headers-dev neostrip-demo', '', d)} \
"

PACKAGES += "${PN}-full-cmdline"
RDEPENDS_${PN}-full-cmdline = " \
    ${PN}-core \
    packagegroup-wild-utils \
    packagegroup-wild-network-utils \
    i2c-tools \
    minicom \
    squashfs-tools \
"
