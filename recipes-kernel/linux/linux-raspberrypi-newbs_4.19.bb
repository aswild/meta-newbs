# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.1"
SRCREV = "c961c5c8cd9a491f5b2611fdad259b2ddc836564"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
