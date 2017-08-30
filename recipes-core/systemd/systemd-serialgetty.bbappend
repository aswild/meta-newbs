# autologin the root user
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://1000-autologin-root.patch"
