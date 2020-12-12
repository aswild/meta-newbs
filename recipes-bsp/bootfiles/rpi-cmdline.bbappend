# meta-raspberrypi builds the cmdline based on a variety of feature-based fragments,
# which isn't really what I want for newbs distros. So instead use NEWBS_CMDLINE which I
# can continue configuring in distro configs rather than just this recipe.
NEWBS_CMDLINE ??= "${CMDLINE}"

do_compile_append() {
    echo '${NEWBS_CMDLINE}' >${WORKDIR}/cmdline.txt
}
