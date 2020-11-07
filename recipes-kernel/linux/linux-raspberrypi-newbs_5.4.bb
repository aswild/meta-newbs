# NEWBS Linux 5.4 kernel

LINUX_VERSION = "5.4.74"
SRCREV = "2ae9829c3da54597a4701285f91985c4d08ee793"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-5.4.y"

require linux-raspberrypi-newbs.inc

SRC_URI += " \
    file://1000-Revert-kbuild-Fail-if-gold-linker-is-detected.patch \
    file://1001-arm64-dts-set-DTC_FLAGS-for-BCM2835-in-main-Makefile.patch \
"
