# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.127"
SRCREV = "cc39f1c9f82f6fe5a437836811d906c709e0661c"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

SRC_URI += " \
    file://1001-arm64-dts-set-DTC_FLAGS-for-BCM2835-in-main-Makefile.patch \
"
