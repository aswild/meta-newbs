# Overlay of firmware.inc in meta-raspberrypi
# Set the same variables, but use the latest version

RPIFW_DATE = "20170703"
RPIFW_MD5 = "2eca01d5faabbab4027ecc797696cb6b"
RPIFW_SHA256 = "74f611ebd418f1e8e11d556e86d2b857e4e2262aab928dd8c6855ef9c22262ff"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
SRC_URI[md5sum] = "${RPIFW_MD5}"
SRC_URI[sha256sum] = "${RPIFW_SHA256}"
RPIFW_S = "${WORKDIR}/firmware-1.${RPIFW_DATE}"
PV = "${RPIFW_DATE}"
