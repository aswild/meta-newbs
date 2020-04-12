# NEWBS Linux 5.5 kernel

LINUX_VERSION = "5.5.15"
SRCREV = "33040662f568580e609c5eda9446c5f663fbf632"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-5.5.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI += " \
    file://0001-arm-dts-change-bcm2711-cma-pool-to-64MB.patch \
    file://0003-Revert-kbuild-Fail-if-gold-linker-is-detected.patch \
"

# Set default DTC_FLAGS through the environment, since the arch Makefiles don't get read
# when Yocto builds dtbs piecemeal. Can't use EXTRA_OEMAKE because Makefile.lib needs
# to append to DTC_FLAGS. Normally this happens in arch/arm64/boot/dts/broadcom/Makefile
export DTC_FLAGS = "-@"

# kernel configcheck is really slow, disable during debugging
#deltask do_kernel_configcheck
