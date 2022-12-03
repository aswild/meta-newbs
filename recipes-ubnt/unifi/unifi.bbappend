# make Java less reluctant to release memory back to the OS so it doesn't
# eat all our RAM
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://heap-free-ratio.patch;patchdir=.."
