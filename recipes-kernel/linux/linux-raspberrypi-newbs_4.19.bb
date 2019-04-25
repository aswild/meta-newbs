# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.36"
SRCREV = "f70d3cee7ea9e6411559cc75e3882d4703752dfe"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
