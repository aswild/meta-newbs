FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += " \
    file://after-local-fs.conf \
    file://1001-rpi-serial-num-machine-id.patch \
"

do_install:append() {
    # systemd-networkd should wait for newbs-nvram (local-fs.target) so that it
    # gets the bind-mounted /etc/systemd/network files
    install -Dm644 ${UNPACKDIR}/after-local-fs.conf \
        ${D}${systemd_unitdir}/system/systemd-networkd.service.d/after-local-fs.conf
}
