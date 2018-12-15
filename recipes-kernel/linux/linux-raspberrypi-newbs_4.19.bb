# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.9"
SRCREV = "0a186de04e03315b1107a5df803bdb7d78828227"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
