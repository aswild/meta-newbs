# don't need cron-like services
RDEPENDS:packagegroup-core-full-cmdline-sys-services:remove = "at cronie"

# RPi doesn't have PCI
RDEPENDS:packagegroup-core-full-cmdline-sys-services:remove = "pciutils"
