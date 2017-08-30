# NEWBS kernel customizations

require recipes-kernel/linux/linux-yocto.inc

DESCRIPTION = "Linux Kernel for Raspberry Pi"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRCPV := "${@d.getVar('SRCREV')[:10]}"
PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "^raspberrypi[23]$"
KCONFIG_MODE ?= "allnoconfig"
LINUX_VERSION_EXTENSION = "-newbs"
DTB_DEPLOYDIR ?= ""

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
