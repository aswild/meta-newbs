# Automounting is fine, but we don't want auto network settings

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://newbs-udev.patch"

RDEPENDS:${PN}:remove = "${PN}-autonet"
