SUMMARY = "Installation scripts and binaries for the closed sourced Raspbery Pi 4 EEPROMs"
LICENSE = "BSD-3-Clause & RPi-EEPROM"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7dcd1a1eb18ae569857c21cae81347cb"
NO_GENERIC_LICENSE[RPi-EEPROM] = "LICENSE"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "^raspberrypi4$"

SRCREV = "3129546271da09dde04da5c9715db909b8e1e417"
PV = "0.1+git${SRCPV}"

SRC_URI = "git://github.com/raspberrypi/rpi-eeprom;protocol=https;branch=master"
S = "${WORKDIR}/git"

FIRMWARE_DIR = "${base_libdir}/firmware/raspberrypi/bootloader"
FILES:${PN} += "${FIRMWARE_DIR}"
RDEPENDS:${PN} = "python3-core python3-modules"

do_configure() {
    # set shebang line to system python3
    sed -i '/#!\s*\//c\#!${bindir}/python3' rpi-eeprom-config

    # use /home for firmware backup directory, since /var/lib is probably read-only
    sed -i '/^FIRMWARE_BACKUP_DIR=/c\FIRMWARE_BACKUP_DIR="/home/rpi-eeprom-backup"' rpi-eeprom-update-default

    # default to stable channel rather than critical
    sed -i '/^FIRMWARE_RELEASE_STATUS=/c\FIRMWARE_RELEASE_STATUS="stable"' rpi-eeprom-update-default
}

do_install() {
    install -Dm755 -t ${D}${bindir} rpi-eeprom-update rpi-eeprom-config
    install -Dm644 rpi-eeprom-update-default ${D}${sysconfdir}/default/rpi-eeprom-update
    install -d ${D}${FIRMWARE_DIR}
    cp -rv firmware/* ${D}${FIRMWARE_DIR}/
    rm -rf ${D}${FIRMWARE_DIR}/old
}
