# NEWBS distro configuration
# vim: ft=bitbake

require conf/distro/wild-generic.conf

# mask dangling appends in meta-raspberrypi
BBMASK += " \
    meta-raspberrypi/recipes-multimedia/gstreamer/.* \
    meta-raspberrypi/recipes-bsp/u-boot/.* \
    meta-raspberrypi/dynamic-layers/meta-python/recipes-connectivity/lirc/.* \
"

# enable licenses
LICENSE_FLAGS_ACCEPTED += "synaptics-killswitch"

# DISTRO_FEATURES_DEFAULT, minus some things
DISTRO_FEATURES = "acl argp ext2 ipv4 ipv6 largefile pci usbhost wifi xattr"

# extra features
DISTRO_FEATURES += "pam usrmerge"

# remove unwanted machine features from meta-raspberrypi
MACHINE_FEATURES:remove = "apm touchscreen alsa bluetooth"

# remove misc recommended packages I don't want
BAD_RECOMMENDATIONS = "vim-tutor"

# don't make /var/log a symlink to /var/volatile/log, we'll handle it manually
# with a bind mount to /home/log (if it exists) or /var/volatile/log (which we'll make)
FILESYSTEM_PERMS_TABLES:remove = "files/fs-perms-volatile-log.txt"

# disable tcmalloc on mongodb, it causes segfaults in dunfell for reasons I don't care to debug
PACKAGECONFIG:remove:pn-mongodb = "tcmalloc"

# disable lmsensors sensord, it pulls in heavy GUI libraries
PACKAGECONFIG:remove:pn-lmsensors = "sensord"
PACKAGECONFIG:remove:pn-lmsensors-config = "sensord"

##### Kernel stuff #####
PREFERRED_PROVIDER_virtual/kernel = "linux-raspberrypi-newbs"
PREFERRED_VERSION_linux-raspberrypi-newbs ?= "6.12%"
KERNEL_IMAGETYPE:arm = "zImage"
KERNEL_IMAGETYPE:aarch64 = "Image"
DTB_DEPLOYDIR = "dtb"

# kernel commandline
NEWBS_ROOT ?= "/dev/mmcblk0p2"

# use systemd net naming scheme v251 to avoid renaming "eth0" into "end0". See systemd.net-naming-scheme(7)
NEWBS_CMDLINE = "console=tty1 console=serial0,115200 root=${NEWBS_ROOT} rootwait earlyprintk net.naming-scheme=v251"
NEWBS_CMDLINE:append:debug = " debug"
SERIAL_CONSOLES = "115200;ttyAMA0"

# kernel device tree
# KERNEL_DEVICETREE is populated from RPI_KERNEL_DEVICETREE and RPI_KERNEL_DEVICETREE_OVERLAYS
# in meta-raspberrypi. KERNEL_DEVICETREE should be populated by the machine config, but customize
# included overlays for newbs here.

# Use only the RPi 4 B dtb, not RPi 400 and CM4
RPI_KERNEL_DEVICETREE:raspberrypi4-64 = "broadcom/bcm2711-rpi-4-b.dtb"
# Same for RPi 5
RPI_KERNEL_DEVICETREE:raspberrypi5 = "broadcom/bcm2712-rpi-5-b.dtb"

NEOSTRIP_OVERLAYS = " \
    overlays/spi0-shiftbrite.dtbo \
    overlays/spi1-shiftbrite.dtbo \
    overlays/spi0-neostrip.dtbo \
    overlays/spi1-neostrip.dtbo \
"

RPI_KERNEL_DEVICETREE_OVERLAYS = " \
    overlays/disable-bt.dtbo \
    overlays/i2c1.dtbo \
    overlays/miniuart-bt.dtbo \
    ${@bb.utils.contains('KERNEL_FEATURES', 'neostrip', '${NEOSTRIP_OVERLAYS}', '', d)} \
"

EXTRA_CONFIG_TXT:append = "${@bb.utils.contains('KERNEL_FEATURES', 'neostrip', \
                             'dtoverlay=spi0-neostrip\ndtparam=neostrip0_length=8\n', '', d)}"


##### Image stuff #####
IMAGE_CLASSES = "newbs-bootimg nimage"
IMAGE_FSTYPES = "ext4 squashfs-zst newbs-bootimg nimage"
IMAGE_FEATURES += "read-only-rootfs"
