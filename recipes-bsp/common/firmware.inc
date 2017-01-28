# the git repo for raspberry pi firmware is >5GB, so just fetch the latest tarball
# which is only ~120MB

#RPIFW_VER = "1.20161125"
#RPIFW_SRC_URI = "https://github.com/raspberrypi/firmware/archive/${RPIFW_VER}.tar.gz"
#RPIFW_MD5 = "a9281d2869e2d7673a83d41bacfa66c2"
#RPIFW_SHA256 = "744e050630621c70991c91e0ee8663dc2f579e743583ca762b13b00bc75859bc"

RPIFW_GITVER = "b846de8b876e07de1829c7425339140eba92b864"
RPIFW_VER = "4.4.45-git+${RPIFW_GITVER}"
RPIFW_SRC_URI = "https://github.com/raspberrypi/firmware/archive/${RPIFW_GITVER}.zip"
RPIFW_MD5 = "b510c89c5ebb3541876575b01cc14089"
RPIFW_SHA256 = "8661a9951ca84e682b68b00ac8bf6ded297de2e2bd48c312f2663d4e831c993d"
RPIFW_S = "${WORKDIR}/firmware-${RPIFW_GITVER}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "${RPIFW_MD5}"
SRC_URI[sha256sum] = "${RPIFW_SHA256}"
PV = "${RPIFW_VER}"
