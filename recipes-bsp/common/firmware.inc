RPIFW_DATE ?= "20181112"
SRCREV ?= "INVALID"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-1.${RPIFW_DATE}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "1d041e337eb0de954989d9f6b4a135ec"
SRC_URI[sha256sum] = "eccb3221fe5fbf9e44e7d1a79b2f401eb7527a41fb8ea7fe7231bc188a8ff50b"

PV = "${RPIFW_DATE}"
