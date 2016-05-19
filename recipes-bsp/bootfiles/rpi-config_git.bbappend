# Add custom configs to top of config.txt

#FILESEXTRAPATHS_prepend := "${THISDIR}:"
#SRC_URI += "file://newbs-config.patch"

do_deploy_append() {
    CONFIG=${DEPLOYDIR}/bcm2835-bootfiles/config.txt

    #echo "kernel=kernel-newbs.img" >>$CONFIG

    if [ "${MACHINE}" == "raspberrypi3" ]; then
        echo "enable_uart=1"      >>$CONFIG
        echo "core_freq=250"      >>$CONFIG
        #echo "dtoverlay=pi3-miniuart-bt"                                 >>$CONFIG
    fi
}
