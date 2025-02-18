SUMMARY = "NEWBS zpi ZFS pool init"

# not really but I don't want to have to screw with LIC_FILES_CHKSUM
LICENSE = "CLOSED"

SRC_URI = "file://zfs-import-zpi.service"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -Dm644 zfs-import-zpi.service ${D}${systemd_unitdir}/system/zfs-import-zpi.service
}

inherit systemd
SYSTEMD_SERVICE:${PN} = "zfs-import-zpi.service"

pkg_postinst:${PN}() {
    cat >>$D${sysconfdir}/fstab <<EOF
zpi/home    /home       zfs rw,nofail,x-systemd.requires=zfs-import.target  0 0
zpi/log     /var/log    zfs rw,nofail,x-systemd.requires=zfs-import.target  0 0
EOF
}
