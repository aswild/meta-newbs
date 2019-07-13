# clobber meta-raspberrypi's config.txt

EXTRA_CONFIG_TXT ??= ""

do_deploy_append_raspberrypi3() {
    CONFIG=${DEPLOYDIR}/bcm2835-bootfiles/config.txt

    tee $CONFIG <<EOF
#### NEWBS CONFIG ####
enable_uart=1
dtoverlay=disable-bt
${@d.getVar('EXTRA_CONFIG_TXT').replace('\\n', '\n').strip()}
EOF

}

do_deploy_append_raspberrypi4-64() {
    tee ${DEPLOYDIR}/bcm2835-bootfiles/config.txt <<EOF
#### NEWBS CONFIG ####
dtoverlay=disable-bt
armstub=armstub8-gic.bin
enable_gic=1
arm_64bit=1
total_mem=1024
${@d.getVar('EXTRA_CONFIG_TXT').replace('\\n', '\n').strip()}
EOF
}
