# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.56"
SRCREV = "9d1deec93fa8b1b4953ff5e9210349f3c85b9a8d"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI += " \
    file://1000-arm64-make-CONFIG_ARCH_BCM2835-select-CONFIG_MFD_COR.patch \
"
