# NEWBS Linux 4.12 kernel

LINUX_VERSION = "4.12.7"
SRCREV = "0d9ed68fbe6e4e8a5f8a580a211f962304395089"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.12.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += " \
    file://defconfig \
    file://shiftbrite-patches.scc \
    ${@bb.utils.contains('DISTRO', 'newbs-mce', 'file://media-drivers.cfg', '', d)} \
"

require linux-raspberrypi-newbs.inc
