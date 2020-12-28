# NEWBS Linux 5.4 kernel

LINUX_VERSION = "5.4.83"
SRCREV = "6da087d0c70c256aeeaa230dd52094d709aef61b"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=https;branch=rpi-5.4.y"

require linux-raspberrypi-newbs.inc

SRC_URI += " \
    file://1000-Revert-kbuild-Fail-if-gold-linker-is-detected.patch \
    file://1001-arm64-dts-set-DTC_FLAGS-for-BCM2835-in-main-Makefile.patch \
"
