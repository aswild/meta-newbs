PACKAGECONFIG:remove = "vdpau"

do_install:append() {
    # Delete an unneeded XML schema file
    rm -f ${D}${datadir}/ffprobe.xsd
}
