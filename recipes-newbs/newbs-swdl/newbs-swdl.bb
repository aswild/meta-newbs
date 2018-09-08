# Yocto recipe for newbs-swdl

DESCRIPTION = "mknImage and NEWBS SWDL"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

NEWBS_SRCNAME = "swdl"
inherit newbs-localsrc

# to let recipes depend on mknImage-native
PROVIDES += "mknImage"

# split mknImage to its own package
PACKAGES =+ "${PN}-mknImage"
FILES_${PN}-mknImage = "${bindir}/mknImage"
RPROVIDES_${PN}-mknImage += "mknImage"

# Yocto finds the glibc dependency and puts it in pkgdata,
# then complains that it can't find it in RDEPENDS.
# Why the fuck is that broken? (only if mknImage is in a split package)
INSANE_SKIP_${PN}-mknImage = "file-rdeps"

# runtime dependencies on the target
RDEPENDS_${PN} = "curl tar gzip xz"

# not sure why yocto checks RDEPENDS for native recipes
RDEPENDS_${PN}_class-native = ""

inherit autotools
CONFIGUREOPT_DEPTRACK = ""
EXTRA_OECONF_class-native = "--disable-swdl"

do_configure_prepend() {
    # clean source directory before building in case I had build objects in the
    # source dir from local development
    if [ -f ${S}/Makefile ]; then
        oe_runmake -C ${S} distclean
    fi
}

BBCLASSEXTEND += "native"
