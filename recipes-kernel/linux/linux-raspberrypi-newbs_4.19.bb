# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.19"
SRCREV = "3cdfbd52d9785d3c11a7a55fe649ef3d62db1ecf"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
