# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.23"
SRCREV = "e2d2941326922b63d722ebc46520c3a2287b675f"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
