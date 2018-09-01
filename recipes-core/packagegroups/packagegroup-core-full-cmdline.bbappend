# don't need cron-like services
RDEPENDS_packagegroup-core-full-cmdline-sys-services_remove = "at cronie"

# RPi doesn't have PCI
RDEPENDS_packagegroup-core-full-cmdline-sys-services_remove = "pciutils"
