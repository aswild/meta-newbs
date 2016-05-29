DESCRIPTION = "NEWBS initramfs /init"
LICENSE = "MIT"
PACKAGE_ARCH = "${MACHINE}"

RDEPENDS_${PN} = " \
    busybox \
"

# add layer base dir to FILESEXTRAPATHS
FILESEXTRAPATHS_prepend := "${THISDIR}/files:${THISDIR}/../..:"
SRC_URI = " \
    file://COPYING.MIT \
    file://init \
"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=df076f38f8fe7485b3f2449e63de23f9"

FILES_${PN} = " \
    /init \
"

addtask unpack_post after do_unpack before do_populate_lic
do_unpack_post() {
    install -d ${S}
    cp -v ${WORKDIR}/init ${S}/
    cp -v ${WORKDIR}/COPYING.MIT ${S}/
}

do_configure() {
    :
}
do_compile() {
    :
}

do_install() {
    install -m 0755 ${WORKDIR}/init ${D}/init
}
