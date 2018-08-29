# Overlay of firmware.inc in meta-raspberrypi
# Set the same variables, but use the latest version

RPIFW_DATE = "20180817"
RPIFW_MD5 = "970a11edd77949ac5e5e8f5348e45456"
RPIFW_SHA256 = "632113ef047ed1bc5e7fb26c955cdfec3cc34f240cb6617a254019a0670f0b3f"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
SRC_URI[md5sum] = "${RPIFW_MD5}"
SRC_URI[sha256sum] = "${RPIFW_SHA256}"
RPIFW_S = "${WORKDIR}/firmware-1.${RPIFW_DATE}"
PV = "${RPIFW_DATE}"
