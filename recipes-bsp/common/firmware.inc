# Overlay of firmware.inc in meta-raspberrypi
# Set the same variables, but use the latest version

RPIFW_DATE = "20171029"
RPIFW_MD5 = "4d27c1888a7bab3097471906e7b4a319"
RPIFW_SHA256 = "46ce28c8d87ef22bdcc57ac1836ca3f04d1ec6f46580ff5a30bf76b3c0822117"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
SRC_URI[md5sum] = "${RPIFW_MD5}"
SRC_URI[sha256sum] = "${RPIFW_SHA256}"
RPIFW_S = "${WORKDIR}/firmware-1.${RPIFW_DATE}"
PV = "${RPIFW_DATE}"
