# Add custom configs to top of config.txt

#FILESEXTRAPATHS_prepend := "${THISDIR}:"
#SRC_URI += "file://newbs-config.patch"

NEWBS_INIT_DEST ?= "newbs-init.cpio.gz"
INITRAMFS_LOAD_ADDR = "0x00c00000"

do_deploy_append() {
    CONFIG=${DEPLOYDIR}/bcm2835-bootfiles/config.txt

    if [ "${MACHINE}" == "raspberrypi3" ]; then
        tee $CONFIG <<EOF

#### NEWBS CONFIG ####
initramfs ${NEWBS_INIT_DEST} ${INITRAMFS_LOAD_ADDR}
enable_uart=1
core_freq=250
dtoverlay=pi3-disable-bt
EOF
    fi
}
