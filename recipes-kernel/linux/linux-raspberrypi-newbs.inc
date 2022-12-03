# NEWBS kernel customizations

inherit kernel
inherit kernel-yocto

SUMMARY = "Linux kernel"
DESCRIPTION = "Linux Kernel for Raspberry Pi"
SECTION = "kernel"
LICENSE = "GPLv2"
HOMEPAGE = "https://github.com/raspberrypi/linux"

SRCPV := "${@d.getVar('SRCREV')[:12]}"
PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "^raspberrypi[234]-64$"
KCONFIG_MODE ?= "allnoconfig"
# note: this needs to match newbs-localversion.cfg or do_kernel_configcheck will complain
LINUX_VERSION_EXTENSION = "-newbs"
DTB_DEPLOYDIR ?= ""

# extra dependencies and compiler args added by linux-yocto.inc for aarch64
DEPENDS += "xz-native bc-native libgcc"
KERNEL_CC += "${TOOLCHAIN_OPTIONS}"
KERNEL_LD += "${TOOLCHAIN_OPTIONS}"

# Configs must be named 'defconfig' or '*.cfg', or be wrapped in an scc
# have to manually expand ${PN} because immediate expansion when parsing the inc file will yield 'defaultpkgname'
# LINUX_VERSION must be set before including this file
LINUX_VERSION_SHORT = "${@'.'.join(d.getVar('LINUX_VERSION').split('.')[:2])}"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-raspberrypi-newbs-${LINUX_VERSION_SHORT}:"
SRC_URI += " \
    file://defconfig-pi4-64.cfg \
    ${@('file://rpi3-64.cfg', '')['raspberrypi4' in d.getVar('MACHINE')]} \
    ${@bb.utils.contains('DISTRO', 'newbs-mce', 'file://media-drivers.cfg', '', d)} \
    file://newbs-localversion.cfg \
"

do_deploy:append() {
    # default Linux recipes copy dtb blobs to DEPLOYDIR, which clutters it
    # archive them all to a subdirectory named DTB_DEPLOYDIR
    # do nothing unless DTB_DEPLOYDIR is set (in a distro conf)
    if [ -n "${DTB_DEPLOYDIR}" ]; then
        cd ${DEPLOYDIR}
        rm -rf ${DTB_DEPLOYDIR}
        install -d ${DTB_DEPLOYDIR}
        for d in *.dtb *.dtbo; do
            echo "archiving $d"
            mv $d ${DTB_DEPLOYDIR}/$d
        done
    fi
}
