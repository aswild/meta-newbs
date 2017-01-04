# NEWBS Linux 4.4 kernel

LINUX_VERSION ?= "4.4.38"
SRCREV = "03f35fe498e5516091e4fd66d1700e8d0b0f4915"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += "file://0001-fix-dtbo-rules.patch"
SRC_URI += "file://defconfig"

require linux-raspberrypi-newbs.inc
