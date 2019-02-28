# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.25"
SRCREV = "7f26b4456f70f9909c19936d550cf7c5dc47e1a5"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
