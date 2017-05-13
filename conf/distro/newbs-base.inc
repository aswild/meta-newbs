# NEWBS distro configuration

##### Yocto Configuration #####

# don't append -glibc to TMPDIR
TCLIBCAPPEND = ""

# don't fail because of dangling appends
BB_DANGLINGAPPENDS_WARNONLY = "1"

##### Kernel stuff #####
PREFERRED_PROVIDER_virtual/kernel = "linux-raspberrypi-newbs"
PREFERRED_VERSION_linux-raspberrypi-newbs = "4.9%"
KERNEL_IMAGETYPE = "zImage"
DTB_DEPLOYDIR = "dtb"

# kernel commandline
NEWBS_ROOT ?= "/dev/mmcblk0p2"
NEWBS_ROOT_TYPE ?= "squashfs-xz"
CMDLINE = "dwc_otg.lpm_enable=0 console=tty1 console=serial0,115200 root=${NEWBS_ROOT} rootwait earlyprintk"
CMDLINE_append_debug = " debug"
SERIAL_CONSOLE = "115200 ttyAMA0"

# kernel device tree
SYSDTB ??= ""
SYSDTB_raspberrypi2 = "bcm2709-rpi-2-b.dtb"
SYSDTB_raspberrypi3 = "bcm2710-rpi-3-b.dtb"
DTOVERLAYS ??= ""
DTOVERLAYS_raspberrypi3 = " \
    overlays/pi3-disable-bt.dtbo \
    overlays/pi3-miniuart-bt.dtbo \
    overlays/spi0-shiftbrite.dtbo \
    overlays/spi1-shiftbrite.dtbo \
    overlays/spi0-neostrip.dtbo \
    overlays/spi1-neostrip.dtbo \
"
KERNEL_DEVICETREE = " \
    ${SYSDTB} \
    ${DTOVERLAYS} \
"

# Wifi firmware
ALTERNATIVE_PRIORITY_linux-firmware-bcm43430 = "50"

##### Root FS stuff #####
DISTRO_FEATURES += " \
    ipv6 \
"

# Enable systemd
DISTRO_FEATURES_BACKFILL_CONSIDERED = "sysvinit"
DISTRO_FEATURES += "systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"

# locales
GLIBC_GENERATE_LOCALES = "en_US.UTF-8"
IMAGE_LINGUAS = "en-us"
DISTRO_FEATURES += "libc-locales libc-charsets libc-locale-code"

DISTROOVERRIDES .= "${@bb.utils.contains('HOST_DEFAULT_PYTHON3', 'y', ':hostpython3', '', d)}"

##### Image stuff #####
IMAGE_CLASSES = "newbs-bootimg"
IMAGE_FEATURES += " \
    read-only-rootfs \
"
IMAGE_FSTYPES = "ext4 squashfs-xz tar.xz newbs-bootimg"
