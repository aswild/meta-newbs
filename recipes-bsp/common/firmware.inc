# Overlay of firmware.inc in meta-raspberrypi
# Set the same variables, but use the latest version

RPIFW_DATE = "20180619"
RPIFW_MD5 = "6c7ff9536cee52253e5fbe54ad963a8b"
RPIFW_SHA256 = "e657478923220947321503ef980f3a6772d6f1e11b48c5c359bdb5e3d49355d9"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
SRC_URI[md5sum] = "${RPIFW_MD5}"
SRC_URI[sha256sum] = "${RPIFW_SHA256}"
RPIFW_S = "${WORKDIR}/firmware-1.${RPIFW_DATE}"
PV = "${RPIFW_DATE}"
