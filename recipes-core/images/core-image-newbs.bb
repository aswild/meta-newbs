SUMMARY = "NEWBS core recovery image"
LICENSE = "MIT"

DEPENDS = "${NEWBS_INIT}"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    kernel-modules \
    packagegroup-newbs-core \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

IMAGE_INSTALL_append_rpi3-wifi = "packagegroup-rpi3-wifi"

newbs_rootfs_postprocess() {
    # Yocto will install the kernel image to /boot, but we don't want that because
    # the boot partition will be mounted in /boot (by fstab in base-files)
    cd ${IMAGE_ROOTFS}
    rm -vf boot/*

    ln -sfv /usr/share/zoneinfo/America/New_York ${IMAGE_ROOTFS}${sysconfdir}/localtime
}
ROOTFS_POSTPROCESS_COMMAND += "newbs_rootfs_postprocess;"


inherit core-image
