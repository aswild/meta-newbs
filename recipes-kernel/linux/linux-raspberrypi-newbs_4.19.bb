# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.1"
SRCREV = "db63de0fbb84c96fa3f4497907dd3953bcf7b9b7"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
