# NEWBS Linux 5.4 kernel

LINUX_VERSION = "5.4.70"
SRCREV = "b2ba07ef46c655e03ac2a869e65ed4ff7d247aac"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-5.4.y"

require linux-raspberrypi-newbs.inc

SRC_URI += " \
    file://1000-Revert-kbuild-Fail-if-gold-linker-is-detected.patch \
    file://1001-arm64-dts-set-DTC_FLAGS-for-BCM2835-in-main-Makefile.patch \
"
