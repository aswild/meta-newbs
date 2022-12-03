# Automounting is fine, but we don't want auto network settings

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://newbs-udev.patch"

do_install:append() {
    rm -f ${D}${sysconfdir}/udev/rules.d/autonet.rules
    rm -f ${D}${sysconfdir}/udev/scripts/network.sh
}
