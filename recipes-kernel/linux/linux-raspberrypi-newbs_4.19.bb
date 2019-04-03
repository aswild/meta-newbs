# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.32"
SRCREV = "1026f584d33f27cf0101590a41d2d8a7e51dedd4"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
