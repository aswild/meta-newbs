# NEWBS Linux 4.9 kernel

LINUX_VERSION = "4.9.41"
SRCREV = "4153f509b449f1c1c816cf124c314975c3daa824"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += " \
    file://defconfig \
    file://shiftbrite-patches.scc \
    ${@bb.utils.contains('DISTRO', 'newbs-mce', 'file://media-drivers.cfg', '', d)} \
"

require linux-raspberrypi-newbs.inc
