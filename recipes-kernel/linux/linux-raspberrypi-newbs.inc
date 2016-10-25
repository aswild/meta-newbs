# NEWBS kernel customizations

inherit kernel siteinfo
require recipes-kernel/linux/linux-yocto.inc
inherit linux-raspberrypi-base

DESCRIPTION = "Linux Kernel for Raspberry Pi"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

PN = "linux-raspberrypi-newbs"
SRCPV := "${d.getVar('SRCREV',True)[:10]}"
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
COMPATIBLE_MACHINE = "raspberrypi[23]"
LINUX_VERSION_EXTENSION = "-NEWBS"
DTB_DEPLOYDIR ?= ""

RPI2_DEFCONFIG = "bcm2709_defconfig"
do_kernel_configme_prepend() {
    cp -vf ${S}/arch/${ARCH}/configs/${RPI2_DEFCONFIG} ${WORKDIR}/defconfig
}

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
        for d in *.dtb *.dtbo; do
            echo "archiving $d"
            mv $d ${DTB_DEPLOYDIR}/$d
        done
    fi
}

do_bundle_initramfs_append() {
    if [ ! -z "${INITRAMFS_IMAGE}" -a x"${INITRAMFS_IMAGE_BUNDLE}" = x1 ]; then
        if test -n "${KERNEL_DEVICETREE}"; then
            for type in ${KERNEL_IMAGETYPES}; do
                # Add RPi bootloader trailer to kernel image to enable DeviceTree support
                ${STAGING_BINDIR_NATIVE}/mkknlimg --dtok ${KERNEL_OUTPUT_DIR}/$type.initramfs \
                                                  ${KERNEL_OUTPUT_DIR}/$type.initramfs
            done
        fi
    fi
}
