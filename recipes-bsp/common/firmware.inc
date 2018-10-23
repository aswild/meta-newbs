RPIFW_DATE ?= "20180924"
SRCREV ?= "INVALID"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-1.${RPIFW_DATE}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "f8c50e6c7ab92b65016b7701ad42172b"
SRC_URI[sha256sum] = "e50f57b88ce09edb09a0a12b16173fced46eeae4e6e948b587af81ce745cf1fc"


PV = "${RPIFW_DATE}"
