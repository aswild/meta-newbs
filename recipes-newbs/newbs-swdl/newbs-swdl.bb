# Yocto recipe for newbs-swdl

DESCRIPTION = "mknImage and NEWBS SWDL"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

PV = "2.0"

NEWBS_SRCNAME = "swdl"
inherit newbs-localsrc

# let recipes depend on mknimage-native
PROVIDES += "mknimage"

# split mknImage to its own package
PACKAGES =+ "mknimage"
FILES_mknimage = "${bindir}/mknImage"

# runtime dependencies on the target
RDEPENDS_${PN} = "curl tar gzip xz"

inherit autotools
CONFIGUREOPT_DEPTRACK = ""

PACKAGECONFIG ?= "swdl"
PACKAGECONFIG_class-native = ""
PACKAGECONFIG[swdl] = "--enable-swdl,--disable-swdl"
PACKAGECONFIG[sanitize] = "--enable-sanitize --disable-lto,,gcc-sanitizers"

do_configure_prepend() {
    # clean source directory before building in case I had build objects
    # in there from local development
    if [ -f ${S}/Makefile ]; then
        oe_runmake -C ${S} distclean || true
    fi
}

BBCLASSEXTEND += "native"
