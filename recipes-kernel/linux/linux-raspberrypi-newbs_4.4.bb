# NEWBS Linux 4.1 kernel

LINUX_VERSION ?= "4.4.11"
SRCREV = "233755da0e7903fccb41f0b8c14e1da5244b69ec"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y"

require linux-raspberrypi-newbs.inc
