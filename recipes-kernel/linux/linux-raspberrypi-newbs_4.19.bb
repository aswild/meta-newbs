# NEWBS Linux 4.19 kernel

LINUX_VERSION = "4.19.4"
SRCREV = "7937f961c9a6c83483ffdc050c410724175b9935"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
