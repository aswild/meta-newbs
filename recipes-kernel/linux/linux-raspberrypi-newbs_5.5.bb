# NEWBS Linux 5.5 kernel

LINUX_VERSION = "5.5.0"
SRCREV = "4017f0bfcf059786929721768690698b146dd812"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-5.5.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI += " \
    file://0001-arm-dts-change-bcm2711-cma-pool-to-64MB.patch \
    file://0002-Makefile-remove-prepare3-from-dtbo-target.patch \
"

# Set default DTC_FLAGS through the environment, since the arch Makefiles don't get read
# when Yocto builds dtbs piecemeal. Can't use EXTRA_OEMAKE because Makefile.lib needs
# to append to DTC_FLAGS. Normally this happens in arch/arm64/boot/dts/broadcom/Makefile
export DTC_FLAGS = "-@"

# Override default set in kernel.bbclass to not try oldnoconfig, it was removed
# in 4.20 04c459d204484fa4747d29c24f00df11fe6334d4 and was an alias for
# olddefconfig anyway
KERNEL_CONFIG_COMMAND = "oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} olddefconfig"

# set KERNEL_LD because ld.gold is unsupported, needed for do_configure and building device trees
# where oe-core tasks don't specify it
EXTRA_OEMAKE += "LD="${KERNEL_LD}""

# kernel configcheck is really slow, disable during debugging
#deltask do_kernel_configcheck
