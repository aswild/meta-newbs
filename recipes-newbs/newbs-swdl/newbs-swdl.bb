# Yocto recipe for newbs-swdl

DESCRIPTION = "mknImage and NEWBS Software Download"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"

PV = "2.1"

NEWBS_SWDL_LOCAL ?= "n"
NEWBS_SWDL_SRCMODE = "${@bb.utils.contains('NEWBS_SWDL_LOCAL', 'y', 'local', 'git', d)}"
require newbs-swdl-${NEWBS_SWDL_SRCMODE}.inc

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

BBCLASSEXTEND += "native"
