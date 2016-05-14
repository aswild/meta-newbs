# Add custom configs to top of config.txt

FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += "file://newbs-config.patch"
