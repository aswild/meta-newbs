# NEWBS kernel customizations

inherit kernel siteinfo
require recipes-kernel/linux/linux-yocto.inc
inherit linux-raspberrypi-base

DESCRIPTION = "Linux Kernel for Raspberry Pi"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PN = "linux-raspberrypi-newbs"
PV = "${LINUX_VERSION}"
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
SRC_URI += " \
            file://defconfig \
"

DEPENDS += "rpi-mkimage-native"
COMPATIBLE_MACHINE = "raspberrypi[23]"
LINUX_VERSION_EXTENSION = "-NEWBS"
DTB_DEPLOYDIR ?= ""

do_deploy_append() {
    # deploy cmdline.txt
    install -d ${DEPLOYDIR}/bcm2835-bootfiles
    echo "${CMDLINE}" >${DEPLOYDIR}/bcm2835-bootfiles/cmdline.txt

    # default Linux recipes copy dtb blobs to DEPLOYDIR, which clutters it
    # archive them all to a subdirectory named DTB_DEPLOYDIR
    # do nothing unless DTB_DEPLOYDIR is set (in a distro conf)
    if [ x${DTB_DEPLOYDIR} != x ]; then
        #cd ${DEPLOY_DIR_IMAGE}
        cd ${DEPLOYDIR}
        rm -rf ${DTB_DEPLOYDIR}
        install -d ${DTB_DEPLOYDIR}
        for d in *.dtb; do
            echo "archiving $d"
            mv $d ${DTB_DEPLOYDIR}/$d
        done
    fi
}

do_rpiboot_mkimage() {
    if test -n "${KERNEL_DEVICETREE}"; then
        # Add RPi bootloader trailer to kernel image to enable DeviceTree support
        ${STAGING_BINDIR_NATIVE}/mkknlimg --dtok ${KERNEL_OUTPUT} ${KERNEL_OUTPUT}
    fi
}

do_bundle_initramfs_append() {
    if [ ! -z "${INITRAMFS_IMAGE}" -a x"${INITRAMFS_IMAGE_BUNDLE}" = x1 ]; then
        if test -n "${KERNEL_DEVICETREE}"; then
            # Add RPi bootloader trailer to kernel image to enable DeviceTree support
            ${STAGING_BINDIR_NATIVE}/mkknlimg --dtok ${KERNEL_OUTPUT}.initramfs ${KERNEL_OUTPUT}.initramfs
        fi
    fi
}

addtask rpiboot_mkimage before do_install after do_compile
