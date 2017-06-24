DESCRIPTION = "NEWBS Recovery Root FS packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES += "${PN}-core"
RDEPENDS_${PN}-core = " \
    packagegroup-wild-core \
    i2c-tools \
    linux-newbs-headers-dev \
    neostrip-demo \
    newbs-nvram \
    newbs-init-util \
    udev-extraconf \
"

PACKAGES += "${PN}-full-cmdline"
RDEPENDS_${PN}-full-cmdline = " \
    ${PN}-core \
    packagegroup-wild-utils \
"
