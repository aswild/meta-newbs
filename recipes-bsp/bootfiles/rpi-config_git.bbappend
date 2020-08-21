# clobber meta-raspberrypi's config.txt

EXTRA_CONFIG_TXT ??= ""

do_deploy_append_raspberrypi3() {
    CONFIG=${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt

    tee $CONFIG <<EOF
#### NEWBS CONFIG ####
enable_uart=1
dtoverlay=disable-bt
${@d.getVar('EXTRA_CONFIG_TXT').replace('\\n', '\n').strip()}
EOF

}

do_deploy_append_raspberrypi4-64() {
    tee ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt <<EOF
#### NEWBS CONFIG ####
dtoverlay=disable-bt
arm_64bit=1
${@d.getVar('EXTRA_CONFIG_TXT').replace('\\n', '\n').strip()}
EOF
}
