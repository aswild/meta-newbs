DESCRIPTION = "Raspberry Pi 3 wifi support"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    linux-firmware-bcm43430 \
    wireless-tools \
    wpa-supplicant \
    iw \
"
