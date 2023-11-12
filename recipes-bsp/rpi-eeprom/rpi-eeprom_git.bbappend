FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://0001-Use-stable-release-status-as-default.patch"

do_install:append() {
    # use stable as default eeprom rather than critical
    rm ${D}${base_libdir}/firmware/raspberrypi/bootloader/default
    ln -s stable ${D}${base_libdir}/firmware/raspberrypi/bootloader/default
}
