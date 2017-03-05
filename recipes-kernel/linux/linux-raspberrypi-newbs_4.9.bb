# NEWBS Linux 4.9 kernel

LINUX_VERSION ?= "4.9.13"
SRCREV = "935c7ce84c982a26f567a03a58a1537424569938"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += " \
    file://defconfig \
    file://shiftbrite-patches.scc \
    file://shiftbrite.cfg \
"

#KBUILD_DEFCONFIG = "bcm2709_defconfig"

require linux-raspberrypi-newbs.inc
