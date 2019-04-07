# NEWBS distro configuration

require conf/distro/wild-generic.conf

# DISTRO_FEATURES_DEFAULT, minus some things
DISTRO_FEATURES = "acl argp ext2 largefile usbhost xattr ${DISTRO_FEATURES_LIBC}"

# extra features
DISTRO_FEATURES += "ld-is-gold pam usrmerge"
DISTRO_FEATURES_append_raspberrypi3 = " wifi"

# remove unwanted machine features from meta-raspberrypi
MACHINE_FEATURES_remove = "apm touchscreen alsa bluetooth"

# remove misc recommended packages I don't want
BAD_RECOMMENDATIONS = "vim-tutor"

##### Kernel stuff #####
PREFERRED_PROVIDER_virtual/kernel = "linux-raspberrypi-newbs"
PREFERRED_VERSION_linux-raspberrypi-newbs ?= "4.19%"
KERNEL_IMAGETYPE_arm = "zImage"
KERNEL_IMAGETYPE_aarch64 = "Image"
DTB_DEPLOYDIR = "dtb"

# kernel commandline
NEWBS_ROOT ?= "/dev/mmcblk0p2"
NEWBS_ROOT_TYPE ?= "squashfs-xz"
# FIXME systemd breaks and kernel panics if console=tty1 and no HDMI connected
#CMDLINE = "dwc_otg.lpm_enable=0 console=tty1 console=serial0,115200 root=${NEWBS_ROOT} rootwait earlyprintk"
CMDLINE = "dwc_otg.lpm_enable=0 console=serial0,115200 root=${NEWBS_ROOT} rootwait earlyprintk"
CMDLINE_append_debug = " debug"
SERIAL_CONSOLE = "115200 ttyAMA0"

# kernel device tree
SYSDTB ??= ""
SYSDTB_raspberrypi2 = "bcm2709-rpi-2-b.dtb"
SYSDTB_raspberrypi3 = "bcm2710-rpi-3-b.dtb \
                       bcm2710-rpi-3-b-plus.dtb"
SYSDTB_raspberrypi3-64 = "broadcom/bcm2710-rpi-3-b.dtb \
                          broadcom/bcm2710-rpi-3-b-plus.dtb"

DTOVERLAYS ??= ""
NEOSTRIP_OVERLAYS = " \
    overlays/spi0-shiftbrite.dtbo \
    overlays/spi1-shiftbrite.dtbo \
    overlays/spi0-neostrip.dtbo \
    overlays/spi1-neostrip.dtbo \
"
DTOVERLAYS_raspberrypi3 = " \
    overlays/pi3-disable-bt.dtbo \
    overlays/pi3-miniuart-bt.dtbo \
    ${@bb.utils.contains('KERNEL_FEATURES', 'neostrip', '${NEOSTRIP_OVERLAYS}', '', d)} \
"
KERNEL_DEVICETREE = " \
    ${SYSDTB} \
    ${DTOVERLAYS} \
"

EXTRA_CONFIG_TXT_append = "${@bb.utils.contains('KERNEL_FEATURES', 'neostrip', \
                             'dtoverlay=spi0-neostrip\ndtparam=neostrip0_length=8\n', '', d)}"


##### Image stuff #####
IMAGE_CLASSES = "nimage"
IMAGE_FSTYPES = "ext4 squashfs-xz nimage"
IMAGE_FEATURES += " \
    read-only-rootfs \
"
