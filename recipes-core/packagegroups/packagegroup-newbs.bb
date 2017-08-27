DESCRIPTION = "NEWBS Recovery Root FS packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES += "${PN}-core"
RDEPENDS_${PN}-core = " \
    packagegroup-wild-core \
    linux-newbs-headers-dev \
    neostrip-demo \
    newbs-nvram \
    newbs-init-util \
    udev-extraconf \
"

PACKAGES += "${PN}-full-cmdline"
RDEPENDS_${PN}-full-cmdline = " \
    ${PN}-core \
    i2c-tools \
    packagegroup-wild-utils \
"
