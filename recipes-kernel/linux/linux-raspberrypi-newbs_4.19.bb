# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.12"
SRCREV = "e75aca6e66f6091dd3b9c316750025c8e9684f16"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
