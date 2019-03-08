# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.27"
SRCREV = "089ae6dda1d91d39f21e223a6413d552be798bce"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
