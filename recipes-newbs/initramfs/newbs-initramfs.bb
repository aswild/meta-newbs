SUMMARY = "Basic single-file initramfs"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit newbs-localsrc
NEWBS_SRCNAME = "init"

DEPENDS = "gzip-native"

EXTRA_OEMAKE = " \
 HOSTCC='${BUILD_CC}' \
 HOSTCFLAGS='${BUILD_CFLAGS}' \
 HOSTLDFLAGS='${BUILD_LDFLAGS}'"

do_compile:prepend() {
    oe_runmake clean || bbwarn "oe_runmake clean failed"
}

do_install() {
    install -d ${D}${systemd_unitdir}/system
    install -m644 lastboot-timestamp.service ${D}${systemd_unitdir}/system/

    if [ "${DISTRO}" = "newbs-unifi" ]; then
        # the unifi controller takes a while to stop, try not to write our timestamp
        # until after it's shut down. This After= assignment implies the inverse
        # when shutting down both units.
        bbnote "UniFi distro, moving unifi.service after lastboot-timestamp.service"
        install -d ${D}${systemd_unitdir}/system/unifi.service.d
        echo -e '[Unit]\nAfter=lastboot-timestamp.service' \
            >${D}${systemd_unitdir}/system/unifi.service.d/after-lastboot-timestamp.conf
    fi
}

ALLOW_EMPTY:${PN} = "1"
PACKAGES =+ "newbs-lastboot-timestamp"
FILES:newbs-lastboot-timestamp = "${systemd_unitdir}/system/*"

inherit systemd
SYSTEMD_PACKAGES = "newbs-lastboot-timestamp"
SYSTEMD_SERVICE:newbs-lastboot-timestamp = "lastboot-timestamp.service"

inherit deploy image-artifact-names
do_deploy() {
    # sanity check - these should be set in image-artifact-names.bbclass
    [ -n "${IMAGE_NAME}" ] || bbfatal "IMAGE_NAME is unset or empty"
    [ -n "${IMAGE_LINK_NAME}" ] || bbfatal "IMAGE_LINK_NAME is unset or empty"

    install -Dm644 ${B}/init.cpio.gz ${DEPLOYDIR}/${IMAGE_NAME}.cpio.gz
    ln -sfv ${IMAGE_NAME}.cpio.gz ${DEPLOYDIR}/${IMAGE_LINK_NAME}.cpio.gz
}
addtask deploy after do_compile
