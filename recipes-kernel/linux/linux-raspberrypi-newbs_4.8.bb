# NEWBS Linux 4.8 kernel

LINUX_VERSION ?= "4.8.15"
SRCREV = "c8af0c2f515556ef052913d552b6b11501c71996"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.8.y"

#SRC_URI += "file://newbs-kernel.cfg"

require linux-raspberrypi-newbs.inc
