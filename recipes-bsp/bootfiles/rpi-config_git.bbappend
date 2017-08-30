# Add custom configs to top of config.txt

NEWBS_INIT_DEST ?= "newbs-init.cpio.gz"
INITRAMFS_LOAD_ADDR = "0x00c00000"
EXTRA_CONFIG_TXT ??= ""

EXTRA_CONFIG_TXT_append = "${@bb.utils.contains('KERNEL_FEATURES', 'neostrip', \
                             'dtoverlay=spi0-neostrip\ndtparam=neostrip0_length=8\n', '', d)}"

do_deploy_append_raspberrypi3() {
    CONFIG=${DEPLOYDIR}/bcm2835-bootfiles/config.txt

    tee $CONFIG <<EOF
#### NEWBS CONFIG ####
enable_uart=1
dtoverlay=pi3-disable-bt

${@d.getVar('EXTRA_CONFIG_TXT').replace('\\n', '\n').strip()}
EOF

}
