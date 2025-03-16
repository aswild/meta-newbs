# remove unnecessary firmware

do_deploy:append:raspberrypi4() {
    # Pi4 doesn't use bootcode.bin, and only needs the '4' versions of start.elf and friends
    rm -v ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/bootcode.bin
    find ${DEPLOYDIR}/${BOOTFILES_DIR_NAME} \( -name 'fixup*.dat' -o -name '*start*.elf' \) -not -name '*4*' \
            -exec rm -v {} \+
}

do_deploy:append:raspberrypi5() {
    # Pi5 does everything in eeprom without bootcode.bin or start.elf
    rm -v ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/bootcode.bin
    rm -v ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/fixup*.dat
    rm -v ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/start*.elf
}
