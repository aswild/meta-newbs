# Add custom configs to top of config.txt

NEWBS_INIT_DEST ?= "newbs-init.cpio.gz"
INITRAMFS_LOAD_ADDR = "0x00c00000"
EXTRA_CONFIG_TXT ??= ""

do_deploy_append_raspberrypi3() {
    CONFIG=${DEPLOYDIR}/bcm2835-bootfiles/config.txt

    tee $CONFIG <<EOF
#### NEWBS CONFIG ####
enable_uart=1
dtoverlay=pi3-disable-bt

dtoverlay=spi0-neostrip
dtparam=neostrip0_length=8
${@d.getVar('EXTRA_CONFIG_TXT').replace('\\n', '\n')}
EOF

}
