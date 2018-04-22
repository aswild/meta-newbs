# Overlay of firmware.inc in meta-raspberrypi
# Set the same variables, but use the latest version

RPIFW_DATE = "20180328"
RPIFW_MD5 = "213b1fae403da2a51841e2af3203e104"
RPIFW_SHA256 = "44731225495bb211353dd86fbead980f8bb62930a3ec6711ed7094aff2397758"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
SRC_URI[md5sum] = "${RPIFW_MD5}"
SRC_URI[sha256sum] = "${RPIFW_SHA256}"
RPIFW_S = "${WORKDIR}/firmware-1.${RPIFW_DATE}"
PV = "${RPIFW_DATE}"
