# the git repo for raspberry pi firmware is >5GB, so just fetch the latest tarball
# which is only 112MB

RPIFW_VER = "1.20160523"
RPIFW_SRC_URI = "https://github.com/raspberrypi/firmware/archive/${RPIFW_VER}.tar.gz"
RPIFW_MD5 = "3db237a9f20602a138e681eb8e67d1ab"
RPIFW_S = "${WORKDIR}/firmware-${RPIFW_VER}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "${RPIFW_MD5}"
PV = "${RPIFW_VER}"
