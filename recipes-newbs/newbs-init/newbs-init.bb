# Yocto recipe for newbs-init

DESCRIPTION = "NEWBS init and utils"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f124bfaefacd4e1a4080065d403bc1d4"

NEWBS_SRCNAME = "newbs-init"
inherit newbs-localsrc

ALLOW_EMPTY_${PN} = "1"
PACKAGES =+ "${PN}-init ${PN}-util"
FILES_${PN}-init = " \
    ${base_sbindir}/newbs-init \
    /init \
    /dev/console \
    /usr/share/newbs.default.txt \
"
FILES_${PN}-util = "${base_sbindir}/newbs-util"
RDEPENDS_${PN}-init = "busybox udev ${PN}-util"

do_configure() {
    ./configure PV="${DISTRO_VERSION}"
}

do_install() {
    install -d ${D}${base_sbindir}
    install -m 755 newbs-init.sh ${D}${base_sbindir}/newbs-init
    install -m 755 newbs-util ${D}${base_sbindir}
    ln -sfv ${base_sbindir}/newbs-init ${D}/init

    install -d ${D}/dev
    mknod -m 622 ${D}/dev/console c 5 1

    install -d ${D}/usr/share
    install -m 644 newbs.default.txt ${D}/usr/share/newbs.default.txt
}
