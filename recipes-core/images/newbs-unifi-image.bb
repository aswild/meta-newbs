require core-image-newbs.bb
SUMMARY = "NEWBS UniFi Controller Image"

SSHD_CONFIG_PERMIT_ROOT_LOGIN = "without-password"

IMAGE_INSTALL += "unifi unifi-iptables-redirect"

HOSTNAME ?= "UniPi"

unipi_postprocess() {
    bbnote "Set hostname to ${HOSTNAME}"
    echo "${HOSTNAME}" > ${IMAGE_ROOTFS}${sysconfdir}/hostname

    if [ -f ${IMAGE_ROOTFS}${sysconfdir}/os-release ] && [ -f ${IMAGE_ROOTFS}${libdir}/unifi/version ]; then
        bbnote "Add UniFi version to os-release PRETTY_NAME"
        unifi_ver="$(cat ${IMAGE_ROOTFS}${libdir}/unifi/version)"
        sed -ri "s/^(PRETTY_NAME=\".*)\"\$/\1 ($unifi_ver)\"/" ${IMAGE_ROOTFS}${sysconfdir}/os-release
    fi
}
ROOTFS_POSTPROCESS_COMMAND += "unipi_postprocess;"
