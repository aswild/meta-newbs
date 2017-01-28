# NEWBS Linux 4.4 kernel

LINUX_VERSION ?= "4.4.45"
SRCREV = "6bd3ceaa9832beb2f100c92ad840bb85dd451d76"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += "file://0001-fix-dtbo-rules.patch"
SRC_URI += "file://newbs.cfg"
#SRC_URI += "file://defconfig"

require linux-raspberrypi-newbs.inc
