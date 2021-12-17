# NEWBS Linux 5.4 kernel

LINUX_VERSION = "5.10.85"
SRCREV = "3ec9b52daa865f1253caf56f243f699c5a796ff7"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=https;branch=rpi-5.10.y"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require linux-raspberrypi-newbs.inc

SRC_URI += " \
    file://1000-Revert-kbuild-Fail-if-gold-linker-is-detected.patch \
    file://1001-arm64-dts-set-DTC_FLAGS-for-BCM2835-in-main-Makefile.patch \
"
