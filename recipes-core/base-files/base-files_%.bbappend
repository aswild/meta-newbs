# NEWBS base files modifications

# pick up the correct fstab and other files from this directory
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

FILES_${PN} += "${sysconfdir}/profile.d/lang.sh"

do_install_append() {
    install -d ${D}${sysconfdir}/profile.d
    echo "export LANG=en_us.UTF-8" >${D}${sysconfdir}/profile.d/lang.sh
}
