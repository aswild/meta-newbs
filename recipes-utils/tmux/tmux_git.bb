SUMMARY = "tmux - the tmux multipliexer"
LICENSE = "BSD"

DEPENDS = "ncurses libevent"

PV = "2.2"

SRC_URI = "git://github.com/tmux/tmux"
SRCREV = "9a4b45dc0fdefead3fe9d471e144da78163860d0"

S = "${WORKDIR}/git"

inherit autotools
