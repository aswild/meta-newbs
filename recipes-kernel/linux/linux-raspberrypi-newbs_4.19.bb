# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.0"
SRCREV = "83bf476e16c7494084431b6f7fd953c096535f5e"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
