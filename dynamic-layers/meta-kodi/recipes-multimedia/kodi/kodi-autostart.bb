SUMMARY = "Autostart infrastructure for Kodi"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.MIT;md5=200dbb8810e8316494ed8036d499d8e7"

SRC_URI = " \
    file://COPYING.MIT \
    file://kodi.service \
    file://80-kodi.rules \
"

S = "${WORKDIR}"

RDEPENDS:${PN} = "kodi"

FILES:${PN} = " \
    ${systemd_unitdir}/system/kodi.service \
    ${sysconfdir}/udev/rules.d/80-kodi.rules \
"

inherit useradd systemd
SYSTEMD_SERVICE:${PN} = "kodi.service"
GROUPADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--gid 1500 kodi"
USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--home-dir /home/kodi --uid 1500 --gid 1500 -G input,tty,video,disk kodi"

do_install() {
    install -m644 -D ${WORKDIR}/kodi.service ${D}${systemd_unitdir}/system/kodi.service
    install -m644 -D ${WORKDIR}/80-kodi.rules ${D}${sysconfdir}/udev/rules.d/80-kodi.rules
}

do_configure[noexec] = "1"
do_compile[noexec] = "1"
