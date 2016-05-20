DESCRIPTION = "NEWBS initramfs /init"
LICENSE = "MIT"
BB_STRICT_CHECKSUM = "0"

RDEPENDS_${PN} = " \
    busybox \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "file://init"

FILES_${PN} = " \
    /init \
"

do_configure() {
    :
}
do_compile() {
    :
}

do_install() {
    install -m 0755 ${WORKDIR}/init ${D}/init
}
