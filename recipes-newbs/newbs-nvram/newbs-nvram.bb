# Dynamic bind mounts

# nvram directory on the target
NEWBS_NVRAM_DIR ?= "/home/newbs-nvram"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3263e5b0ff3ce562fdbd8938623c5508"

PV = "1.0"
PR = "2"
SRC_URI = " \
    file://LICENSE \
    file://newbs-nvram \
    file://newbs-nvram.service.in \
"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

do_configure[noexec] = "1"
do_compille[noexec] = "1"

do_install() {
    install -Dm755 newbs-nvram ${D}${libdir}/newbs/newbs-nvram

    install -d ${D}${systemd_unitdir}/system
    sed "s|@NEWBS_NVRAM_DIR@|${NEWBS_NVRAM_DIR}|" newbs-nvram.service.in \
        >${D}${systemd_unitdir}/system/newbs-nvram.service

    install -d ${D}${sysconfdir}/default
    echo "NEWBS_NVRAM_DIR=\"${NEWBS_NVRAM_DIR}\"" >${D}${sysconfdir}/default/newbs-nvram
}

FILES:${PN} = " \
    ${libdir}/newbs/* \
    ${sysconfdir}/default/newbs-nvram \
    ${systemd_unitdir}/system/* \
"

RDEPENDS:${PN} = "bash volatile-binds"

inherit allarch systemd
SYSTEMD_SERVICE:${PN} = "newbs-nvram.service"
