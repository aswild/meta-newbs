require core-image-newbs.bb
SUMMARY = "NEWBS UniFi Controller Image"

SSHD_CONFIG_PERMIT_ROOT_LOGIN = "without-password"

IMAGE_INSTALL += " \
    packagegroup-wild-network-utils \
    iptables \
    unifi \
"
