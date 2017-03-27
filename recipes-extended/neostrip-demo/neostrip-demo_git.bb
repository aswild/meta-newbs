# neostrip demo program

DESCRIPTION = "neostrip demo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=939d9a033a64e3ba0460a4c6d5185e1f"

SRC_URI = "git:///workspace/neostrip-demo"
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

DEPENDS += "linux-newbs-headers"

do_compile() {
    oe_runmake KINCLUDE= all
}

do_install() {
    oe_runmake DESTDIR=${D} install
}

do_configure[noexec] = "1"
