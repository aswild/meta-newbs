# neostrip demo program

DESCRIPTION = "neostrip demo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=939d9a033a64e3ba0460a4c6d5185e1f"

NEWBS_SRCNAME = "neostrip-demo"
inherit newbs-localsrc

DEPENDS += "linux-newbs-headers"

do_compile() {
    oe_runmake KINCLUDE= all
}

do_install() {
    oe_runmake DESTDIR=${D} install
}

do_configure[noexec] = "1"
