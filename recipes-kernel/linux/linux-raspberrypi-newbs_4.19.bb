# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.7"
SRCREV = "172a80a6804086350ee594765d43047a69f4755f"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
