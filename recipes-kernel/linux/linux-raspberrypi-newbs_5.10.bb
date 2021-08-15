# NEWBS Linux 5.4 kernel

LINUX_VERSION = "5.10.52"
SRCREV = "9d6d498b5b5471fe9bab3e60bf2800c7490241b9"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=https;branch=rpi-5.10.y"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-raspberrypi-newbs.inc

SRC_URI += " \
    file://1000-Revert-kbuild-Fail-if-gold-linker-is-detected.patch \
    file://1001-arm64-dts-set-DTC_FLAGS-for-BCM2835-in-main-Makefile.patch \
"
