# Dynamic bind mounts

# nvram directory on the target
NEWBS_NVRAM_DIR ?= "/home/newbs-nvram"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3263e5b0ff3ce562fdbd8938623c5508"

PV = "1.0"
PR = "2"
SRC_URI = " \
    file://LICENSE \
    file://newbs-nvram \
    file://newbs-nvram.service.in \
    file://var-log-mount.sh \
    file://var-log-mount.service\
"

S = "${WORKDIR}"

do_configure[noexec] = "1"
do_compille[noexec] = "1"

do_install() {
    install -Dm755 newbs-nvram ${D}${libdir}/newbs/newbs-nvram

    install -d ${D}${systemd_unitdir}/system
    sed "s|@NEWBS_NVRAM_DIR@|${NEWBS_NVRAM_DIR}|" newbs-nvram.service.in \
        >${D}${systemd_unitdir}/system/newbs-nvram.service

    install -d ${D}${sysconfdir}/default
    echo "NEWBS_NVRAM_DIR=\"${NEWBS_NVRAM_DIR}\"" >${D}${sysconfdir}/default/newbs-nvram

    bbnote "installing var-log-mount service"
    install -Dm755 var-log-mount.sh ${D}${libdir}/newbs/var-log-mount
    install -Dm644 var-log-mount.service ${D}${systemd_unitdir}/system/var-log-mount.service
}

FILES_${PN} = " \
    ${libdir}/newbs/* \
    ${sysconfdir}/default/newbs-nvram \
    ${systemd_unitdir}/system/* \
"

RDEPENDS_${PN} = "bash volatile-binds"

inherit allarch systemd
SYSTEMD_SERVICE_${PN} = "newbs-nvram.service var-log-mount.service"
