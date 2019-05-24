# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.44"
SRCREV = "d2b14d32d3e417e5ef9434bb8ec9f1229035ee2e"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
