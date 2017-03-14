# NEWBS Linux 4.9 kernel

LINUX_VERSION = "4.9.14"
SRCREV = "a599f69212b051db4cd00a02f9312dc897beba70"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += " \
    file://defconfig \
    file://shiftbrite-patches.scc \
"

#KBUILD_DEFCONFIG = "bcm2709_defconfig"

require linux-raspberrypi-newbs.inc
