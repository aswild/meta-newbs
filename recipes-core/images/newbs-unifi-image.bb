require core-image-newbs.bb
SUMMARY = "NEWBS UniFi Controller Image"

SSHD_CONFIG_PERMIT_ROOT_LOGIN = "without-password"

IMAGE_INSTALL += "unifi"

HOSTNAME ?= "UniPi"

unipi_set_hostname() {
    bbnote "Set hostname to ${HOSTNAME}"
    echo "${HOSTNAME}" > ${IMAGE_ROOTFS}${sysconfdir}/hostname
}
ROOTFS_POSTPROCESS_COMMAND += "unipi_set_hostname;"
