# NEWBS Linux 4.12 kernel (upstream autorev)

LINUX_VERSION = "4.12.9"
SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.12.y"

KBUILD_DEFCONFIG = "bcm2709_defconfig"
KBUILD_DEFCONFIG_aarch64 = "bcmrpi3_defconfig"
KCONFIG_MODE = "alldefconfig"

KERNEL_DEVICETREE_remove = "overlays/spi0-shiftbrite.dtbo overlays/spi1-shiftbrite.dtbo overlays/spi0-neostrip.dtbo overlays/spi1-neostrip.dtbo"

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi-newbs-${PV}:"
SRC_URI += " \
    file://0022-thermal-Enable-BCM2835-thermal-driver-for-RPi3-64-bi.patch \
"

require linux-raspberrypi-newbs.inc

