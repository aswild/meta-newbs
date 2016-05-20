SUMMARY = "NEWBS initramfs image"
LICENSE = "MIT"

require recipes-core/images/core-image-minimal-initramfs.bb

PACKAGE_INSTALL_remove = " \
    initramfs-live-boot \
    initramfs-live-install \
    initramfs-live-install-efi \
"

PACKAGE_INSTALL_append = " \
    newbs-init \
"

export IMAGE_BASENAME = "newbs-init-image"
