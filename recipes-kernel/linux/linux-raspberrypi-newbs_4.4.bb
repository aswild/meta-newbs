# NEWBS Linux 4.4 kernel

LINUX_VERSION ?= "4.4.48"
SRCREV = "fb50a274e277b6e25588bf96fd8ed8814f4d95c6"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += "file://0001-fix-dtbo-rules.patch \
            file://1000-shiftbrite-rgb-led-driver.patch \
            file://defconfig \
"

require linux-raspberrypi-newbs.inc
