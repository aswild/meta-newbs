# remove unnecessary firmware

do_deploy_append() {
    local _not
    case "${MACHINE}" in
        raspberrypi4*)
            _not='-not'
            # bootcode.bin is not used on Pi4
            rm -v ${DEPLOYDIR}/${PN}/bootcode.bin
            ;;
        *) _not='' ;;
    esac
    find ${DEPLOYDIR}/${PN} \( -name 'fixup*.dat' -o -name '*start*.elf' \) $_not -name '*4*' \
            -exec rm -v {} \;
}
