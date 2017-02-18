# NEWBS Linux 4.4 kernel

LINUX_VERSION ?= "4.4.49"
SRCREV = "8d1dd639a4db6ccc416bb0a00afc7a8888018b3d"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += "file://0001-fix-dtbo-rules.patch \
            file://1000-shiftbrite-rgb-led-driver.patch \
            file://defconfig \
"

require linux-raspberrypi-newbs.inc
