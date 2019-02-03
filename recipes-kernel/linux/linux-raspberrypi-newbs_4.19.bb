# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.17"
SRCREV = "8ef59203d79d074689a16fc831a95ad07cab3e47"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
