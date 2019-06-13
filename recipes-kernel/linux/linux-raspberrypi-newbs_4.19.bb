# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.50"
SRCREV = "5040b4b78e4cb74a6364d9a7c6cca0385e2dffd8"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
