# Common NEWBS iamge rootfs features

inherit core-image
inherit wild-image-postprocess

# BCJ filters for squashfs
EXTRA_IMAGECMD_squashfs-xz_append_arm = "-Xbcj arm"
EXTRA_IMAGECMD_squashfs-xz_append_aarch64 = "-Xbcj arm"

symlink_dd_to_sdcard() {
    if [ ! -e "${DEPLOY_DIR_IMAGE}/dd-to-sdcard.sh" ]; then
        ln -sv "${NEWBSROOT}/meta-newbs/dd-to-sdcard.sh" ${DEPLOY_DIR_IMAGE}
    fi
}
IMAGE_POSTPROCESS_COMMAND_append = " symlink_dd_to_sdcard;"
