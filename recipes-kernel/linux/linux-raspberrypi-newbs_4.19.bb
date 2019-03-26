# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.30"
SRCREV = "344d205460ddea60bdc50c5bb4dff4679bf3d1e1"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
