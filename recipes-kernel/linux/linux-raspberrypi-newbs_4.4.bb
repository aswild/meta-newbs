# NEWBS Linux 4.4 kernel

LINUX_VERSION ?= "4.4.21"
SRCREV = "raspberrypi-kernel_1.20160921-1"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += "file://0001-fix-dtbo-rules.patch"

require linux-raspberrypi-newbs.inc
