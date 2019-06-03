require core-image-newbs.bb

IMAGE_FEATURES += "dev-pkgs doc-pkgs tools-sdk"
IMAGE_INSTALL += " \
    packagegroup-core-buildessential \
    gdb \
    kernel-devsrc \
    man \
    python3 \
    strace \
"

symlink_pyth() {
    if [ ! -e ${IMAGE_ROOTFS}${bindir}/python ] && [ -e ${IMAGE_ROOTFS}${bindir}/python3 ]; then
        ln -s python3 ${IMAGE_ROOTFS}${bindir}/python
    fi
}
ROOTFS_POSTPROCESS_COMMAND += "symlink_pyth;"
