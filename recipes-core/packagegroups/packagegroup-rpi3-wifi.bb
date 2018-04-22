DESCRIPTION = "Raspberry Pi 3 wifi support"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    linux-firmware-raspbian-bcm43430 \
    linux-firmware-raspbian-bcm43455 \
    wireless-tools \
    wpa-supplicant \
    iw \
"
