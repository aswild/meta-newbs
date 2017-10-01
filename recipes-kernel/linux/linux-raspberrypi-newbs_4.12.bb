# NEWBS Linux 4.12 kernel

LINUX_VERSION = "4.12.14"
SRCREV = "6fe4193dd99f1e299e56c9fa6ba43681398cc041"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.12.y"

#FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

require linux-raspberrypi-newbs.inc
