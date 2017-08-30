# NEWBS Linux 4.12 kernel

LINUX_VERSION = "4.12.9"
SRCREV = "ef79e7bb67c80f0b20a8327692f590a54568eda5"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.12.y"

# Configs must be named 'defconfig' or '*.cfg', or be wrapped in an scc
NEWBS_DEFCONFIG_arm = "defconfig.cfg"
NEWBS_DEFCONFIG_aarch64 = "defconfig-arm64.cfg"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI += " \
    file://${NEWBS_DEFCONFIG} \
    file://newbs-patches.scc \
    ${@bb.utils.contains('DISTRO', 'newbs-mce', 'file://media-drivers.cfg', '', d)} \
"

require linux-raspberrypi-newbs.inc
