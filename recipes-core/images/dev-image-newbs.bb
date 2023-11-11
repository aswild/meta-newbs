require core-image-newbs.bb

IMAGE_FEATURES += "dev-pkgs doc-pkgs tools-sdk"
IMAGE_INSTALL += " \
    packagegroup-wild-devtools \
"

IMAGE_INSTALL:append:raspberrypi4 = " rpi-eeprom"

symlink_pyth() {
    if [ ! -e ${IMAGE_ROOTFS}${bindir}/python ] && [ -e ${IMAGE_ROOTFS}${bindir}/python3 ]; then
        ln -s python3 ${IMAGE_ROOTFS}${bindir}/python
    fi
}
ROOTFS_POSTPROCESS_COMMAND += "symlink_pyth;"
