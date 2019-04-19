# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.34"
SRCREV = "6dabe04c82943c34fb5bcadc9cc2ee7b6b4a145b"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
