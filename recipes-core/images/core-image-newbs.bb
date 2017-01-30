SUMMARY = "NEWBS core recovery image"
LICENSE = "MIT"

#DEPENDS = "${NEWBS_INIT}"

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    kernel-modules \
    packagegroup-newbs-core \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

IMAGE_INSTALL_append_raspberrypi3 = "packagegroup-rpi3-wifi"

copy_ssh_host_keys() {
    if [ -n "${SSH_HOST_KEYS}" ]; then
        for key in ${SSH_HOST_KEYS}; do
            install -d ${IMAGE_ROOTFS}${sysconfdir}/ssh
            cp -vf ${key} ${IMAGE_ROOTFS}${sysconfdir}/ssh/
        done
    fi
}
# prepend this command so that it runs before read_only_rootfs_hook
ROOTFS_POSTPROCESS_COMMAND_prepend = "copy_ssh_host_keys; "

newbs_rootfs_postprocess() {
    # Yocto will install the kernel image to /boot, but we don't want that because
    # the boot partition will be mounted in /boot (by fstab in base-files)
    rm -fv ${IMAGE_ROOTFS}/boot/*

    # set timezone to match the build host
    localtime_file=${sysconfdir}/localtime
    if [ -L $localtime_file ]; then
        host_tz=`readlink -f $localtime_file`
        if [ -n ${host_tz} ] && [ -e ${IMAGE_ROOTFS}${host_tz} ]; then
            echo "Setting timezone to ${host_tz}"
            ln -sfv ${host_tz} ${IMAGE_ROOTFS}${localtime_file}
        fi
    fi
}
ROOTFS_POSTPROCESS_append = " newbs_rootfs_postprocess;"

inherit core-image
