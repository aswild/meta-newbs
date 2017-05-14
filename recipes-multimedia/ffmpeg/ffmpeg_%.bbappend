PACKAGECONFIG_remove = "vdpau"

do_install_append() {
    # Delete an unneeded XML schema file
    rm -f ${D}${datadir}/ffprobe.xsd
}
