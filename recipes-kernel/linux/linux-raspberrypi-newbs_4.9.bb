# NEWBS Linux 4.9 kernel

LINUX_VERSION ?= "4.9.0"
SRCREV = "aa5014ae84c80ceac1561ceb30e060c88d9598d4"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y"

#SRC_URI += "file://newbs-kernel.cfg"

require linux-raspberrypi-newbs.inc

#KERNEL_DEFCONFIG_raspberrypi3 = "bcmrpi3_defconfig"
