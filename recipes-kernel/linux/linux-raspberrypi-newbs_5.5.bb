# NEWBS Linux 5.5 kernel

LINUX_VERSION = "5.5.0"
SRCREV = "4017f0bfcf059786929721768690698b146dd812"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-5.5.y"

require linux-raspberrypi-newbs.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI += " \
    file://1001-arm64-dts-set-DTC_FLAGS-for-BCM2835-in-main-Makefile.patch \
    file://1002-arm-dts-change-bcm2711-cma-pool-to-64MB.patch \
    file://1003-Makefile-remove-prepare3-from-dtbo-target.patch \
"

# Override default set in kernel.bbclass:
#   * don't try oldnoconfig, it was removed in 4.20 04c459d204484fa4747d29c24f00df11fe6334d4
#     and was an alias for olddefconfig anyway
KERNEL_CONFIG_COMMAND = "oe_runmake_call -C ${S} CC="${KERNEL_CC}" O=${B} olddefconfig"

# set KERNEL_LD because ld.gold is unsupported, needed for do_configure and building device trees
# where oe-core tasks don't specify it
EXTRA_OEMAKE += "LD="${KERNEL_LD}""
