# the git repo for raspberry pi firmware is >5GB, so just fetch the latest tarball
# which is only 112MB

RPIFW_VER = "1.20160921"
RPIFW_SRC_URI = "https://github.com/raspberrypi/firmware/archive/${RPIFW_VER}.tar.gz"
RPIFW_MD5 = "07c3ce1e4a83c9e66de0a6db60357961"
RPIFW_SHA256 = "68a43bd62ddfc377b04c24c341107a2ec2e6b7b176c21588f65cee30020cfea9"
RPIFW_S = "${WORKDIR}/firmware-${RPIFW_VER}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "${RPIFW_MD5}"
SRC_URI[sha256sum] = "${RPIFW_SHA256}"
PV = "${RPIFW_VER}"
