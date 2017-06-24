# NEWBS Linux 4.9 kernel

LINUX_VERSION = "4.9.33"
SRCREV = "3c1164ccbadb34377d7cbf330b37dd903fef70de"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += " \
    file://defconfig \
    file://shiftbrite-patches.scc \
    ${@bb.utils.contains('DISTRO', 'newbs-mce', 'file://media-drivers.cfg', '', d)} \
"

require linux-raspberrypi-newbs.inc
