SUMMARY = "tmux - the tmux multipliexer"
LICENSE = "ISC"

DEPENDS = "ncurses libevent"

PV = "2.2"

SRC_URI = "git://github.com/tmux/tmux"
SRCREV = "9a4b45dc0fdefead3fe9d471e144da78163860d0"
LIC_FILES_CHKSUM = "file://COPYING;md5=f7d9aab84ec6567139a4755c48d147fb"

S = "${WORKDIR}/git"

inherit autotools

# DIRTY HACK ALERT!
# --with-libtool-sysroot is broken in tmux, so prepend staging dir
# to all paths manually
python __anonymous() {
    opts = d.getVar("CONFIGUREOPTS", True).split()
    prefix = d.getVar("STAGING_DIR_HOST", True)
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
    libtoolize --automake
    ./autogen.sh || die "autogen.sh failed"

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
