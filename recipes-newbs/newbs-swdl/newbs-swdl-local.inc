# fetch newbs-swdl from the local workspace

NEWBS_SRCNAME = "swdl"
inherit newbs-localsrc

do_configure_prepend() {
    # clean source directory before building in case I had build objects
    # in there from local development
    if [ -f ${S}/Makefile ]; then
        oe_runmake -C ${S} distclean || true
    fi
}
