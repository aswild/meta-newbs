# NEWBS Linux 4.12 kernel

LINUX_VERSION = "4.12.10"
SRCREV = "29a81e20c5f8f52d460a9d87938f482736201730"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.12.y"

#FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

require linux-raspberrypi-newbs.inc
