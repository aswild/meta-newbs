DESCRIPTION = "Init service to user persistent /var/log on read-only-rootfs distros"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://var-log-mount.sh;endline=4;md5=ea3ff529321c0c1cb097a06434988f55"

SRC_URI = " \
    file://var-log-mount.sh \
    file://var-log-mount.service\
"

S = "${WORKDIR}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -Dm755 var-log-mount.sh ${D}${libdir}/newbs/var-log-mount
    install -Dm644 var-log-mount.service ${D}${systemd_unitdir}/system/var-log-mount.service
}

inherit distro_features_check systemd
REQUIRED_DISTRO_FEATURES = "systemd"
SYSTEMD_SERVICE_${PN} = "var-log-mount.service"

FILES_${PN} += "${libdir}/newbs/*"
RDEPENDS_${PN} += "/bin/bash"
