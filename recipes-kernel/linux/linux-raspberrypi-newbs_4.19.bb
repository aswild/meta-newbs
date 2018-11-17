# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.2"
SRCREV = "f2bea78388b8d0abb5583b5d945b4a86d1ad667f"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
