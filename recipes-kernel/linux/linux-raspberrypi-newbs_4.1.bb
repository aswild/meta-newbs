# NEWBS Linux 4.1 kernel

LINUX_VERSION ?= "4.1.21"
SRCREV = "ff45bc0e8917c77461b2901e2743e6339bb70413"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.1.y"

require linux-raspberrypi-newbs.inc
