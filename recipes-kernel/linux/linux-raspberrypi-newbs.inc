# NEWBS kernel customizations

require recipes-kernel/linux/linux-yocto.inc

DESCRIPTION = "Linux Kernel for Raspberry Pi"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRCPV := "${@d.getVar('SRCREV')[:12]}"
PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "^raspberrypi[234]-64$"
KCONFIG_MODE ?= "allnoconfig"
# note: this needs to match newbs-localversion.cfg or do_kernel_configcheck will complain
LINUX_VERSION_EXTENSION = "-newbs"
DTB_DEPLOYDIR ?= ""

# Configs must be named 'defconfig' or '*.cfg', or be wrapped in an scc
# have to manually expand ${PN} because immediate expansion when parsing the inc file will yield 'defaultpkgname'
# LINUX_VERSION must be set before including this file
LINUX_VERSION_SHORT = "${@'.'.join(d.getVar('LINUX_VERSION').split('.')[:2])}"
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi-newbs-${LINUX_VERSION_SHORT}:"
SRC_URI += " \
    file://defconfig-pi4-64.cfg \
    ${@('file://rpi3-64.cfg', '')['raspberrypi4' in d.getVar('MACHINE')]} \
    ${@bb.utils.contains('DISTRO', 'newbs-mce', 'file://media-drivers.cfg', '', d)} \
    file://newbs-localversion.cfg \
"

do_deploy_append() {
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
