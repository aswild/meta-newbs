SUMMARY = "Utility to backup and restore partitions"
LICENSE = "GPLv2"

DEPENDS = "e2fsprogs dosfstools ncurses"
RDEPENDS_${PN} = "e2fsprogs ncurses"

PV = "0.2.88"
SRC_URI = "git://github.com/Thomas-Tsai/partclone.git;nobranch=1;tag=${PV}"

LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"

S = "${WORKDIR}/git"

inherit autotools gettext

EXTRA_OECONF = " \
    --enable-ncursesw \
    --enable-extfs \
    --enable-fat \
"

# DIRTY HACK ALERT!
# --with-libtool-sysroot is broken in partclone, so prepend staging dir
# to all paths manually
python __anonymous() {
    opts = d.getVar("CONFIGUREOPTS", True).split()
    prefix = d.getVar("D", True)
    if prefix[-1] == "/":
        prefix = prefix[:-1]

    for i in range(len(opts)):
        opt = opts[i].split("=")
        if opt[0][-3:] == "dir":
            newval = prefix + opt[1]
            opts[i] = "%s=%s"%(opt[0], newval)

    d.setVar("CONFIGUREOPTS", " ".join(opts))
}

do_configure() {
    cd ${S}
    set -x
    oe_runconf
}

do_compile() {
    cd ${S}
    oe_runmake
}

do_install() {
    cd ${S}
    oe_runmake install
}
