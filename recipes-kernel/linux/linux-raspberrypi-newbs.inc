# NEWBS kernel customizations

require recipes-kernel/linux/linux-yocto.inc

DESCRIPTION = "Linux Kernel for Raspberry Pi"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRCPV := "${@d.getVar('SRCREV')[:10]}"
PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "^raspberrypi[234]$"
KCONFIG_MODE ?= "allnoconfig"
LINUX_VERSION_EXTENSION = "-newbs"
DTB_DEPLOYDIR ?= ""

# Configs must be named 'defconfig' or '*.cfg', or be wrapped in an scc
NEWBS_DEFCONFIG_arm = "defconfig.cfg"
NEWBS_DEFCONFIG_aarch64 = "defconfig-arm64.cfg"

# have to manually expand ${PN} because immediate expansion when parsing the inc file will yield 'defaultpkgname'
# LINUX_VERSION must be set before including this file
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi-newbs-${@'.'.join(d.getVar('LINUX_VERSION').split('.')[:2])}:"
SRC_URI += " \
    file://${NEWBS_DEFCONFIG} \
    file://newbs-patches.scc \
    ${@bb.utils.contains('DISTRO', 'newbs-mce', 'file://media-drivers.cfg', '', d)} \
"
SRC_URI_append_raspberrypi4-64 = " file://rpi4-64.cfg"

do_deploy_append() {
    # deploy cmdline.txt
    install -d ${DEPLOYDIR}/bcm2835-bootfiles
    echo "${CMDLINE}" >${DEPLOYDIR}/bcm2835-bootfiles/cmdline.txt

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
