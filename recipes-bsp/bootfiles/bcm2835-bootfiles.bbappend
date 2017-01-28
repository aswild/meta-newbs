# remove unneeded files

#do_deploy_append_raspberrypi2() {
#    rm -vf ${DEPLOYDIR}/${PN}/bcm2708-rpi-b.dtb
#    rm -vf ${DEPLOYDIR}/${PN}/bcm2708-rpi-b-plus.dtb
#    rm -vf ${DEPLOYDIR}/${PN}/bcm2708-rpi-cm.dtb
#    if [ "${MACHINE}" = "raspberrypi2" ]; then
#        rm -vf ${DEPLOYDIR}/${PN}/bcm2709-rpi-3-b.dtb
#        rm -vf ${DEPLOYDIR}/${PN}/bcm2709-rpi-cm3.dtb
#    fi
#}
#
#do_deploy_append_raspberrypi3() {
#    rm -vf ${DEPLOYDIR}/${PN}/bcm2709-rpi-2-b.dtb
#}
