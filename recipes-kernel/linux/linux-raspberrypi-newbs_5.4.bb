# NEWBS Linux 5.4 kernel

LINUX_VERSION = "5.4.77"
SRCREV = "b8681a08ba16b15cc9f010bef2a24ffac0b054d1"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=https;branch=rpi-5.4.y"

require linux-raspberrypi-newbs.inc

SRC_URI += " \
    file://1000-Revert-kbuild-Fail-if-gold-linker-is-detected.patch \
    file://1001-arm64-dts-set-DTC_FLAGS-for-BCM2835-in-main-Makefile.patch \
"
