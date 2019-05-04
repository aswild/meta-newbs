# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.37"
SRCREV = "74f464c6c40dad45d3f13b06c85b5b4bde9db667"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
