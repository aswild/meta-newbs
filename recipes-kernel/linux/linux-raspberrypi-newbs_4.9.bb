# NEWBS Linux 4.9 kernel

LINUX_VERSION = "4.9.17"
SRCREV = "cd6413a82a66de6ecce828ce67df4f6e3290ea86"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += " \
    file://defconfig \
    file://shiftbrite-patches.scc \
"

#KBUILD_DEFCONFIG = "bcm2709_defconfig"

require linux-raspberrypi-newbs.inc
