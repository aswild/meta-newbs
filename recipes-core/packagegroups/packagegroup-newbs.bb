DESCRIPTION = "NEWBS Recovery Root FS packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES += "${PN}-core"
RDEPENDS_${PN}-core = " \
    packagegroup-wild-core \
    newbs-nvram \
    newbs-init-util \
    udev-extraconf \
    ${@bb.utils.contains('KERNEL_FEATURES', 'neostrip', 'linux-newbs-headers-dev neostrip-demo', '', d)} \
"

PACKAGES += "${PN}-full-cmdline"
RDEPENDS_${PN}-full-cmdline = " \
    ${PN}-core \
    i2c-tools \
    packagegroup-wild-utils \
"
