# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.15"
SRCREV = "8b9654689ab115ae16f5a154de29d7016e469eb6"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
