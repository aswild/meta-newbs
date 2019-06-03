# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.46"
SRCREV = "55e0334cf5a43948b65788980cd8ab729427230e"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
