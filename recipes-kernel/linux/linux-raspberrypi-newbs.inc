# NEWBS kernel customizations

require recipes-kernel/linux/linux-yocto.inc
inherit linux-raspberrypi-base

DESCRIPTION = "Linux Kernel for Raspberry Pi"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PN = "linux-raspberrypi-newbs"
SRCPV := "${@d.getVar('SRCREV',True)[:10]}"
PV = "${LINUX_VERSION}+${SRCPV}"
python __anonymous() {
    import re
    pv = d.getVar("PV", True)
    m = re.match(r"(\d+\.\d+)(.\d+)?", pv)
    if m is not None:
        d.setVar("PV_SHORT", m.group(1))
    else:
        d.setVar("PV_SHORT", pv)
}
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV_SHORT}:"

COMPATIBLE_MACHINE = "raspberrypi[23]"
#KBUILD_DEFCONFIG_raspberrypi2 ?= "bcm2709_defconfig"
#KBUILD_DEFCONFIG_raspberrypi3 ?= "bcm2709_defconfig"
KCONFIG_MODE = "alldefconfig"
LINUX_VERSION_EXTENSION = "-newbs"
DTB_DEPLOYDIR ?= ""

do_deploy_append() {
    # deploy cmdline.txt
    install -d ${DEPLOYDIR}/bcm2835-bootfiles
    echo "${CMDLINE}" >${DEPLOYDIR}/bcm2835-bootfiles/cmdline.txt

    # default Linux recipes copy dtb blobs to DEPLOYDIR, which clutters it
    # archive them all to a subdirectory named DTB_DEPLOYDIR
    # do nothing unless DTB_DEPLOYDIR is set (in a distro conf)
    if [ x${DTB_DEPLOYDIR} != x ]; then
        cd ${DEPLOYDIR}
        rm -rf ${DTB_DEPLOYDIR}
        install -d ${DTB_DEPLOYDIR}
        for d in *.dtb *.dtbo; do
            echo "archiving $d"
            mv $d ${DTB_DEPLOYDIR}/$d
        done
    fi
}
