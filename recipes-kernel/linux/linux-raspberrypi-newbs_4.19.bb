# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.17"
SRCREV = "806d6240729e09e4a04ccf038f1f3847e92ba8dd"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
