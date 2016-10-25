DESCRIPTION = "Raspberry Pi 3 wifi support"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    linux-firmware-brcm43430 \
    wireless-tools \
    iw \
"
