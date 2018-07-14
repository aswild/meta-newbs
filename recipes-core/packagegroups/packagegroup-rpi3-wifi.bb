DESCRIPTION = "Raspberry Pi 3 wifi support"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    linux-firmware-bcm43430 \
    linux-firmware-bcm43455 \
    linux-firmware-bcm43430a1-hcd \
    linux-firmware-bcm4345c0-hcd \
    wireless-tools \
    wpa-supplicant \
    iw \
"
