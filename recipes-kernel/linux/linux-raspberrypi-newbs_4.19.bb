# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.20"
SRCREV = "078e8aa41285fde8f6f22f1fa8071bfb37240f4c"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
