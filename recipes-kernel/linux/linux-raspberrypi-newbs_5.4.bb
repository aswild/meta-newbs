# NEWBS Linux 5.4 kernel

LINUX_VERSION = "5.4.61"
SRCREV = "2ce8c3ab0f9d1ffb67310ffd200be82d80a8d13d"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-5.4.y"

require linux-raspberrypi-newbs.inc

SRC_URI += " \
    file://1000-Revert-kbuild-Fail-if-gold-linker-is-detected.patch \
    file://1001-arm64-dts-set-DTC_FLAGS-for-BCM2835-in-main-Makefile.patch \
"
