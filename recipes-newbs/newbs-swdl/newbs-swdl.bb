# Yocto recipe for newbs-swdl

DESCRIPTION = "mknImage and NEWBS SWDL"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

NEWBS_SRCNAME = "swdl"
inherit newbs-localsrc

# to let recipes depend on mknimage-native
PROVIDES += "mknimage"

# split mknImage to its own package
PACKAGES =+ "${PN}-mknimage"
FILES_${PN}-mknimage = "${bindir}/mknImage"
RPROVIDES_${PN}-mknimage += "mknimage"

# runtime dependencies on the target
RDEPENDS_${PN} = "curl tar gzip xz"

inherit autotools
CONFIGUREOPT_DEPTRACK = ""
EXTRA_OECONF_class-native = "--disable-swdl"

do_configure_prepend() {
    # clean source directory before building in case I had build objects
    # in there from local development
    if [ -f ${S}/Makefile ]; then
        oe_runmake -C ${S} distclean || true
    fi
}

BBCLASSEXTEND += "native"
