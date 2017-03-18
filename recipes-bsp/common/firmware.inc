# Overlay of firmware.inc in meta-raspberrypi
# Set the same variables, but use the latest version

RPIFW_DATE = "20170303"
RPIFW_MD5 = "c87bc1bffbb8f254fc739e34baa03b02"
RPIFW_SHA256 = "ee8758d7e63c8fea7c8b7b476ff1c09f9591c74349b7751036a78ae5b88eca36"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
SRC_URI[md5sum] = "${RPIFW_MD5}"
SRC_URI[sha256sum] = "${RPIFW_SHA256}"
RPIFW_S = "${WORKDIR}/firmware-1.${RPIFW_DATE}"
PV = "${RPIFW_DATE}"
