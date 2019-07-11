# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.57"
SRCREV = "55b89745a4782db50e86291bd1adee8fb44b84ad"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI += " \
    file://1000-arm64-make-CONFIG_ARCH_BCM2835-select-CONFIG_MFD_COR.patch \
    file://1001-arm64-dts-set-DTC_FLAGS-for-BCM2835-in-main-Makefile.patch \
"
