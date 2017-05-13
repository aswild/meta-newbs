# Overlay of firmware.inc in meta-raspberrypi
# Set the same variables, but use the latest version

RPIFW_DATE = "20170427"
RPIFW_MD5 = "d7a87af3ffc7ad26c939f01b7ffd561e"
RPIFW_SHA256 = "e253084463a0a3ba908396ac383169397d53bc55307d0d8471f2814f2458fa1b"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
SRC_URI[md5sum] = "${RPIFW_MD5}"
SRC_URI[sha256sum] = "${RPIFW_SHA256}"
RPIFW_S = "${WORKDIR}/firmware-1.${RPIFW_DATE}"
PV = "${RPIFW_DATE}"
