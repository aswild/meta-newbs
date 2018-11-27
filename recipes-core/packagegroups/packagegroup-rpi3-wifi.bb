DESCRIPTION = "Raspberry Pi 3 wifi support"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    linux-firmware-rpidistro-bcm43430 \
    linux-firmware-rpidistro-bcm43455 \
    bluez-firmware-rpidistro-bcm43430a1-hcd \
    bluez-firmware-rpidistro-bcm4345c0-hcd \
    wpa-supplicant \
    iw \
"
