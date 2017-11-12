# Common NEWBS iamge rootfs features

inherit core-image

# BCJ filters for squashfs
EXTRA_IMAGECMD_squashfs-xz_append_arm = "-Xbcj arm"
EXTRA_IMAGECMD_squashfs-xz_append_aarch64 = "-Xbcj arm"

copy_ssh_host_keys() {
    install -d ${IMAGE_ROOTFS}${sysconfdir}/ssh
    if [ -n "${SSH_HOST_KEYS}" ]; then
        for key in ${SSH_HOST_KEYS}; do
            cp -vf ${key} ${IMAGE_ROOTFS}${sysconfdir}/ssh/
        done
    else
        # If an SSH key path isn't set, create empty files anyway so
        # newbs-nvram can bind-mount over them
        for key in ssh_host_{dsa,ecdsa,ed25519,rsa}_key{,.pub}; do
            echo "Creating empty ${IMAGE_ROOTFS}${sysconfdir}/ssh/${key}"
            touch ${IMAGE_ROOTFS}${sysconfdir}/ssh/${key}
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

    # make zsh or bash the default shell
    if [ -x ${IMAGE_ROOTFS}/bin/zsh ]; then
        bbnote "Setting /bin/zsh as default root shell"
        sed -i '/^root/s|/bin/sh$|/bin/zsh|' ${IMAGE_ROOTFS}/etc/passwd
    elif [ -x ${IMAGE_ROOTFS}/bin/bash -o -L ${IMAGE_ROOTFS}/bin/bash ]; then
        # /bin/bash might be a symlink to /bin/bash.bash
        bbnote "Setting /bin/bash as default root shell"
        sed -i '/^root/s|/bin/sh$|/bin/bash|' ${IMAGE_ROOTFS}/etc/passwd
    fi

    # Use systemd-networkd and systemd-resolvd
    rm -rvf ${IMAGE_ROOTFS}${sysconfdir}network

    if echo "${MACHINE}" | grep -q raspberrypi3; then
        install -d ${IMAGE_ROOTFS}${sysconfdir}/wpa_supplicant
        ln -sfv /run/systemd/resolve/resolv.conf ${IMAGE_ROOTFS}${sysconfdir}/resolv.conf
        bbnote "Enabling wpa_supplicant@wlan0.service"
        # systemctl --root=${IMAGE_ROOTFS} enable wpa_supplicant@wlan0.service
        ln -sfv ../../../../lib/systemd/system/wpa_supplicant@.service \
                ${IMAGE_ROOTFS}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@wlan0.service
    fi

    # use systemd-resolved for /etc/resolv.conf
    if [ -f ${IMAGE_ROOTFS}/lib/systemd/systemd-resolved ]; then
        rm -f ${IMAGE_ROOTFS}/etc/resolv.conf
        ln -s /run/systemd/resolve/resolv.conf ${IMAGE_ROOTFS}/etc/resolv.conf
    fi

    # Make /media a symlink to /run/media
    rm -rf ${IMAGE_ROOTFS}/media
    ln -sfv run/media ${IMAGE_ROOTFS}/media
}
ROOTFS_POSTPROCESS_COMMAND_append = " newbs_rootfs_postprocess;"

symlink_dd_to_sdcard() {
    if [ ! -e "${DEPLOY_DIR_IMAGE}/dd-to-sdcard.sh" ]; then
        ln -sv "${NEWBSROOT}/meta-newbs/dd-to-sdcard.sh" ${DEPLOY_DIR_IMAGE}
    fi
}
IMAGE_POSTPROCESS_COMMAND_append = " symlink_dd_to_sdcard;"

# Don't spam DEPLOYDIR with testdata.json files. Unfortunately for us,
# rootfs-postcommands.bbclass adds "write_image_test_data ;" and that space makes it so
# that typical _remove syntax doesn't work right
python __anonymous() {
    cmd = d.getVar('ROOTFS_POSTPROCESS_COMMAND')
    cmd = cmd.replace('write_image_test_data ;', '')
    d.setVar('ROOTFS_POSTPROCESS_COMMAND', cmd)
}
