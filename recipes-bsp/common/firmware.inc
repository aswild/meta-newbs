# the git repo for raspberry pi firmware is >5GB, so just fetch the latest tarball
# which is only 112MB

RPIFW_VER = "1.20161125"
RPIFW_SRC_URI = "https://github.com/raspberrypi/firmware/archive/${RPIFW_VER}.tar.gz"
RPIFW_MD5 = "a9281d2869e2d7673a83d41bacfa66c2"
RPIFW_SHA256 = "744e050630621c70991c91e0ee8663dc2f579e743583ca762b13b00bc75859bc"
RPIFW_S = "${WORKDIR}/firmware-${RPIFW_VER}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "${RPIFW_MD5}"
SRC_URI[sha256sum] = "${RPIFW_SHA256}"
PV = "${RPIFW_VER}"
