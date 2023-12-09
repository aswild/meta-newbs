# default to standard variant for more paths
do_install:append() {
    ln -s cyfmac43455-sdio-standard.bin ${D}${nonarch_base_libdir}/firmware/cypress/cyfmac43455-sdio.bin
}
