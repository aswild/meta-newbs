# Yocto recipe for newbs-init

DESCRIPTION = "NEWBS init and utils"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f124bfaefacd4e1a4080065d403bc1d4"

SRC_URI = "git://github.com/aswild/newbs-init;branch=master"
SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

PACKAGES += "${PN}-util"
FILES_${PN} = " \
    ${base_sbindir}/newbs-init \
    /init \
    /dev/console \
    /usr/share/newbs.default.txt \
"
FILES_${PN}-util = "${base_sbindir}/newbs-util"
RDEPENDS_${PN} = "busybox udev ${PN}-util"

do_configure() {
    ./configure PV="${DISTRO_VERSION}"
}

do_compile() {
    # use -e flag to use CC/CFLAGS/LDFLAGS from the environment
    oe_runmake -e
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

# build using local/external source if it exists in the workspace
# (rather than having to push to github or edit in WORKDIR)
def get_externalsrc_dir(d):
    srcdir = d.getVar('NEWBSROOT') + '/newbs-init-source'
    if os.path.isdir(srcdir):
        return srcdir
    else:
        return ''

inherit externalsrc
EXTERNALSRC = "${@get_externalsrc_dir(d)}"
EXTERNALSRC_BUILD = "${@get_externalsrc_dir(d)}"
