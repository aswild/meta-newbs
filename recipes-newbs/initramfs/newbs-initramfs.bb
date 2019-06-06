SUMMARY = "Basic single-file initramfs"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit newbs-localsrc
NEWBS_SRCNAME = "init"

DEPENDS = "gzip-native"

EXTRA_OEMAKE = " \
 HOSTCC='${BUILD_CC}' \
 HOSTCFLAGS='${BUILD_CFLAGS}' \
 HOSTLDFLAGS='${BUILD_LDFLAGS}'"

inherit deploy nopackages

do_configure[noexec] = "1"
do_install[noexec] = "1"

do_compile_prepend() {
    oe_runmake clean || bbwarn "oe_runmake clean failed"
}

do_deploy() {
    rm -rf ${DEPLOYDIR}
    install -d ${DEPLOYDIR}

    # IMAGE_BASENAME, IMAGE_NAME, and IMAGE_LINK_NAME are all set sanely in bitbake.conf
    install -Dm644 ${S}/init.cpio.gz ${DEPLOYDIR}/${IMAGE_NAME}.cpio.gz
    ln -sfv ${IMAGE_NAME}.cpio.gz ${DEPLOYDIR}/${IMAGE_LINK_NAME}.cpio.gz
}
addtask deploy after do_compile
