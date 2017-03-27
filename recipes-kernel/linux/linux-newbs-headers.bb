DESCRIPTION = "Recipe to install neostrip and shiftbrite headers for userspace"
LICENSE = "GPLv2"

do_compile[depends] += "virtual/kernel:do_shared_workdir"

do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"

do_compile() {
    mkdir -p ${B}
    oe_runmake -C ${STAGING_KERNEL_DIR} INSTALL_HDR_PATH=${B} headers_install
}

do_install() {
    install -d ${D}${includedir}/spi
    install -m 644 ${B}/include/spi/neostrip.h ${D}${includedir}/spi
    install -m 644 ${B}/include/spi/shiftbrite.h ${D}${includedir}/spi
}

ALLOW_EMPTY_${PN} = "1"
