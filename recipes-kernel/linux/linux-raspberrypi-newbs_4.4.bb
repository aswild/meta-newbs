# NEWBS Linux 4.1 kernel

LINUX_VERSION ?= "4.4.13"
SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y"

require linux-raspberrypi-newbs.inc
