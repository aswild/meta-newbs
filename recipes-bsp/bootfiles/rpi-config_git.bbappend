# clobber meta-raspberrypi's config.txt

EXTRA_CONFIG_TXT ??= ""

do_deploy_append_raspberrypi3() {
    CONFIG=${DEPLOYDIR}/bcm2835-bootfiles/config.txt

    tee $CONFIG <<EOF
#### NEWBS CONFIG ####
enable_uart=1
dtoverlay=pi3-disable-bt
${@d.getVar('EXTRA_CONFIG_TXT').replace('\\n', '\n').strip()}
EOF

}
