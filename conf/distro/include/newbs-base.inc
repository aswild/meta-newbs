# NEWBS distro configuration
# vim: ft=bitbake

require conf/distro/wild-generic.conf

# mask dangling appends in meta-raspberrypi
BBMASK += " \
    meta-raspberrypi/recipes-multimedia/gstreamer/.* \
    meta-raspberrypi/recipes-bsp/u-boot/.* \
    meta-raspberrypi/dynamic-layers/meta-python/recipes-connectivity/lirc/.* \
"

# DISTRO_FEATURES_DEFAULT, minus some things
DISTRO_FEATURES = "acl argp ext2 largefile pci usbhost xattr ${DISTRO_FEATURES_LIBC}"

# extra features
DISTRO_FEATURES += "ld-is-gold pam usrmerge"
DISTRO_FEATURES_append_raspberrypi3 = " wifi"
DISTRO_FEATURES_append_raspberrypi4 = " wifi"

# remove unwanted machine features from meta-raspberrypi
MACHINE_FEATURES_remove = "apm touchscreen alsa bluetooth"

# remove misc recommended packages I don't want
BAD_RECOMMENDATIONS = "vim-tutor"

# don't make /var/log a symlink to /var/volatile/log, we'll handle it manually
# with a bind mount to /home/log (if it exists) or /var/volatile/log (which we'll make)
VOLATILE_LOG_DIR = "no"

# disable tcmalloc on mongodb, it causes segfaults in dunfell for reasons I don't care to debug
PACKAGECONFIG_remove_pn-mongodb = "tcmalloc"

# prefer the prebuilt borg rather than compiling from source and installing python
PREFERRED_PROVIDER_borgbackup ?= "borgbackup-bin"

##### Kernel stuff #####
PREFERRED_PROVIDER_virtual/kernel = "linux-raspberrypi-newbs"
PREFERRED_VERSION_linux-raspberrypi-newbs ?= "5.10%"
KERNEL_IMAGETYPE_arm = "zImage"
KERNEL_IMAGETYPE_aarch64 = "Image"
DTB_DEPLOYDIR = "dtb"

# kernel commandline
NEWBS_ROOT ?= "/dev/mmcblk0p2"
# FIXME systemd breaks and kernel panics if console=tty1 and no HDMI connected
#CMDLINE = "dwc_otg.lpm_enable=0 console=tty1 console=serial0,115200 root=${NEWBS_ROOT} rootwait earlyprintk"
NEWBS_CMDLINE = "dwc_otg.lpm_enable=0 console=serial0,115200 root=${NEWBS_ROOT} rootwait earlyprintk"
NEWBS_CMDLINE_append_debug = " debug"
SERIAL_CONSOLES = "115200;ttyAMA0"

# kernel device tree
# KERNEL_DEVICETREE is populated from RPI_KERNEL_DEVICETREE and RPI_KERNEL_DEVICETREE_OVERLAYS
# in meta-raspberrypi. KERNEL_DEVICETREE should be populated by the machine config, but customize
# included overlays for newbs here.
NEOSTRIP_OVERLAYS = " \
    overlays/spi0-shiftbrite.dtbo \
    overlays/spi1-shiftbrite.dtbo \
    overlays/spi0-neostrip.dtbo \
    overlays/spi1-neostrip.dtbo \
"

RPI_KERNEL_DEVICETREE_OVERLAYS = " \
    overlays/disable-bt.dtbo \
    overlays/miniuart-bt.dtbo \
    ${@bb.utils.contains('KERNEL_FEATURES', 'neostrip', '${NEOSTRIP_OVERLAYS}', '', d)} \
"

EXTRA_CONFIG_TXT_append = "${@bb.utils.contains('KERNEL_FEATURES', 'neostrip', \
                             'dtoverlay=spi0-neostrip\ndtparam=neostrip0_length=8\n', '', d)}"


##### Image stuff #####
IMAGE_CLASSES = "newbs-bootimg nimage squashfs-zstd"
IMAGE_FSTYPES = "ext4 squashfs-zstd newbs-bootimg nimage"
IMAGE_FEATURES += " \
    read-only-rootfs \
"
