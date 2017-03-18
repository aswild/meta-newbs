# NEWBS Linux 4.9 kernel

LINUX_VERSION = "4.9.16"
SRCREV = "8bf13deebd582fd64a6595d23e9c965b652ef7c8"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += " \
    file://defconfig \
    file://shiftbrite-patches.scc \
"

#KBUILD_DEFCONFIG = "bcm2709_defconfig"

require linux-raspberrypi-newbs.inc
