# fetch newbs-swdl from github

SRC_URI = "git://github.com/aswild/newbs-source;branch=master;protocol=https"
SRCREV = "${AUTOREV}"
PV .= "+git${SRCPV}"

S = "${WORKDIR}/git/swdl"
