# Common NEWBS iamge rootfs features

inherit core-image

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

    # Mark the rootfs as rw in fstab. This gives us rw when booting ext4,
    # and squashfs will mount ro always anyway
    sed -i '/\/dev\/root/s/\bro\b/rw/' ${IMAGE_ROOTFS}/etc/fstab

    # set timezone to match the build host
    localtime_file=${sysconfdir}/localtime
    if [ -L $localtime_file ]; then
        host_tz=`readlink -f $localtime_file`
        if [ -n ${host_tz} ] && [ -e ${IMAGE_ROOTFS}${host_tz} ]; then
            bbnote "Setting timezone to ${host_tz}"
            ln -sfv ${host_tz} ${IMAGE_ROOTFS}${localtime_file}
        fi
    fi

    # make zsh the default shell
    if [ -x ${IMAGE_ROOTFS}/bin/zsh ]; then
        bbnote "Setting /bin/zsh as default root shell"
        sed -i '/^root/s|/bin/sh$|/bin/zsh|' ${IMAGE_ROOTFS}/etc/passwd
    fi

    # Use systemd-networkd and systemd-resolvd
    rm -rvf ${IMAGE_ROOTFS}${sysconfdir}network

    if [ "${MACHINE}" = "raspberrypi3" ]; then
        install -d ${IMAGE_ROOTFS}${sysconfdir}/wpa_supplicant
        ln -sfv /run/systemd/resolve/resolv.conf ${IMAGE_ROOTFS}${sysconfdir}/resolv.conf
        bbnote "Enabling wpa_supplicant@wlan0.service"
        # systemctl --root=${IMAGE_ROOTFS} enable wpa_supplicant@wlan0.service
        ln -sfv ../../../../lib/systemd/system/wpa_supplicant@.service \
                ${IMAGE_ROOTFS}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@wlan0.service
    fi

    # Make /media a symlink to /run/media
    rm -rf ${IMAGE_ROOTFS}/media
    ln -sfv run/media ${IMAGE_ROOTFS}/media
}
ROOTFS_POSTPROCESS_COMMAND_append = " newbs_rootfs_postprocess;"
