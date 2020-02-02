# from meta-openembedded/meta-oe
require recipes-support/libgpiod/libgpiod.inc

DEPENDS += "autoconf-archive-native"

SRC_URI[md5sum] = "dbcebfe6ff29ef4db33ed87ff7ff6d65"
SRC_URI[sha256sum] = "9549583cc897126c6a7c592f4020d5f522003562d9e43c8165336c698838da57"

SRC_URI += "file://0001-libgpiod-python-shared-only.patch"

# enable tools, python, and cxx bindings
PACKAGECONFIG ?= "cxx tools python3"

PACKAGECONFIG[cxx] = "--enable-bindings-cxx,--disable-bindings-cxx"
PACKAGECONFIG[tests] = "--enable-tests,--disable-tests,kmod udev"

PACKAGECONFIG[python3] = "--enable-bindings-python,--disable-bindings-python,python3"

PACKAGES =+ "${PN}-cxx"
FILES_${PN}-cxx = "${libdir}/libgpiodcxx${SOLIBS}"

inherit python3native

PACKAGES =+ "${PN}-python"
FILES_${PN}-python = "${PYTHON_SITEPACKAGES_DIR}"
RRECOMMENDS_PYTHON = "${@bb.utils.contains('PACKAGECONFIG', 'python3', '${PN}-python', '',d)}"
RRECOMMENDS_${PN}-python += "${RRECOMMENDS_PYTHON}"
