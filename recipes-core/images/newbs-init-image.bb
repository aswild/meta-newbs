SUMMARY = "NEWBS initramfs image"
LICENSE = "MIT"

require recipes-core/images/core-image-minimal-initramfs.bb

COMPATIBLE_HOST = "arm.*-linux"

PACKAGE_INSTALL_remove = " \
    initramfs-live-boot \
    initramfs-live-install \
    initramfs-live-install-efi \
"

PACKAGE_INSTALL_append = " \
    newbs-init \
"

export IMAGE_BASENAME = "newbs-init-image"
