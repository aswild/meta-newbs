# Automounting is fine, but we don't want auto network settings

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
    file://mountflags-shared.conf \
"

FILES_${PN} += " \
    ${systemd_unitdir}/system/systemd-udevd.service.d/mountflags-shared.conf \
    ${sysconfdir}/udev/mount.blacklist.d/sdcard \
"

do_install_append() {
    rm -f ${D}${sysconfdir}/udev/rules.d/autonet.rules
    rm -f ${D}${sysconfdir}/udev/scripts/network.sh

    install -m644 -D ${WORKDIR}/mountflags-shared.conf \
                     ${D}${systemd_unitdir}/system/systemd-udevd.service.d/mountflags-shared.conf

    install -d ${D}${sysconfdir}/udev/mount.blacklist.d
    echo "/dev/mmcblk0p*" >${D}${sysconfdir}/udev/mount.blacklist.d/sdcard
}
