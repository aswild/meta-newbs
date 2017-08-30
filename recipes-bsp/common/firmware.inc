# Overlay of firmware.inc in meta-raspberrypi
# Set the same variables, but use the latest version

RPIFW_DATE = "20170811"
RPIFW_MD5 = "afd09f9a6df14e32b6d832fd9f51d087"
RPIFW_SHA256 = "a25f6281d64732892a2e838cc2346f1a88505b5c77a57a6540755362ea64043a"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
SRC_URI[md5sum] = "${RPIFW_MD5}"
SRC_URI[sha256sum] = "${RPIFW_SHA256}"
RPIFW_S = "${WORKDIR}/firmware-1.${RPIFW_DATE}"
PV = "${RPIFW_DATE}"
