# This essentially adds the features of init-ifupdown, but with
# native systemd services instead of the init script

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
    file://interfaces \
    file://ifup.service \
"

inherit systemd

SYSTEMD_SERVICE_${PN} = "ifup.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

do_install_append() {
    install -d ${D}${sysconfdir}/network/interfaces.d \
               ${D}${sysconfdir}/network/if-pre-up.d \
               ${D}${sysconfdir}/network/if-up.d \
               ${D}${sysconfdir}/network/if-down.d \
               ${D}${sysconfdir}/network/if-post-down.d \
               ${D}${systemd_unitdir}/system

    install -m 0644 ${WORKDIR}/interfaces ${D}${sysconfdir}/network/interfaces
    install -m 0644 ${WORKDIR}/ifup.service ${D}${systemd_unitdir}/system
}
