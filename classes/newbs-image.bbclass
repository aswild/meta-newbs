# Common NEWBS image rootfs features

inherit core-image
inherit wild-image-postprocess

# BCJ filters for squashfs
EXTRA_IMAGECMD_squashfs-xz_append_arm = "-Xbcj arm"
EXTRA_IMAGECMD_squashfs-xz_append_aarch64 = "-Xbcj arm"

# whether to enable wpa_supplicant@wlan0.service
RPI_WIFI_AUTOSTART ?= "0"

newbs_rootfs_postprocess() {
    # make sure we have /etc/wpa_supplicant, mostly so newbs-nvram can bind over it
    install -d ${IMAGE_ROOTFS}${sysconfdir}/wpa_supplicant
    if [ "${RPI_WIFI_AUTOSTART}" = "1" ]; then
        bbnote "Enabling wpa_supplicant@wlan0.service"
        # systemctl --root=${IMAGE_ROOTFS} enable wpa_supplicant@wlan0.service
        ln -sfv ../../../../lib/systemd/system/wpa_supplicant@.service \
                ${IMAGE_ROOTFS}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@wlan0.service
    fi
}
ROOTFS_POSTPROCESS_COMMAND_append = " newbs_rootfs_postprocess;"

symlink_dd_to_sdcard() {
    if [ ! -e "${DEPLOY_DIR_IMAGE}/dd-to-sdcard.sh" ]; then
        ln -sv "${NEWBSROOT}/meta-newbs/dd-to-sdcard.sh" ${DEPLOY_DIR_IMAGE}
    fi
}
IMAGE_POSTPROCESS_COMMAND_append = " symlink_dd_to_sdcard;"
