# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.58"
SRCREV = "246113692edbef9a438b31ab2dd0172a30ed5eb2"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI += " \
    file://1000-arm64-make-CONFIG_ARCH_BCM2835-select-CONFIG_MFD_COR.patch \
    file://1001-arm64-dts-set-DTC_FLAGS-for-BCM2835-in-main-Makefile.patch \
"
