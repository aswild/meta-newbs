# redirect incoming port 443 to 8443

IPTABLES_SERVICE = "iptables-webserver-redirect.service"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://${IPTABLES_SERVICE}"
FILES_${PN} += "${systemd_unitdir}/system/${IPTABLES_SERVICE}"
SYSTEMD_SERVICE_${PN} += "${IPTABLES_SERVICE}"

do_install_append() {
    install -Dm644 ${WORKDIR}/${IPTABLES_SERVICE} \
            ${D}${systemd_unitdir}/system/${IPTABLES_SERVICE}
}
