# Dynamic bind mounts

# nvram directory on the target
NEWBS_NVRAM_DIR ?= "/home/newbs-nvram"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3263e5b0ff3ce562fdbd8938623c5508"

PV = "1.0"
SRC_URI = " \
    file://LICENSE \
    file://newbs-nvram \
    file://newbs-nvram.service.in \
"

FILES_${PN} = " \
    ${sbindir}/newbs-nvram \
    ${sysconfdir}/default/newbs-nvram \
    ${systemd_unitdir}/system/newbs-nvram.service \
"

RDEPENDS_${PN} = "bash volatile-binds"
SYSTEMD_SERVICE_${PN} = "newbs-nvram.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sbindir}
    install -m 0755 newbs-nvram ${D}${sbindir}/

    install -d ${D}${systemd_unitdir}/system
    sed "s|@NEWBS_NVRAM_DIR@|${NEWBS_NVRAM_DIR}|" newbs-nvram.service.in \
        >${D}${systemd_unitdir}/system/newbs-nvram.service

    install -d ${D}${sysconfdir}/default
    echo "NEWBS_NVRAM_DIR=\"${NEWBS_NVRAM_DIR}\"" >${D}${sysconfdir}/default/newbs-nvram
}

inherit allarch systemd
do_configure[noexec] = "1"
do_compille[noexec] = "1"
