# Overlay of firmware.inc in meta-raspberrypi
# Set the same variables, but use the latest version

RPIFW_DATE = "20170215"
RPIFW_MD5 = "e0ce7be125ee567f7e804897e5eeff72"
RPIFW_SHA256 = "98970367e26b7b618baf9feb1aafdd29a59e63145f5cae4e9a6596c71773cb04"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
SRC_URI[md5sum] = "${RPIFW_MD5}"
SRC_URI[sha256sum] = "${RPIFW_SHA256}"
RPIFW_S = "${WORKDIR}/firmware-1.${RPIFW_DATE}"
PV = "${RPIFW_DATE}"
