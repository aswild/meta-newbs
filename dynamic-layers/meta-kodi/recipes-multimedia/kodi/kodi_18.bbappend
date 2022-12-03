# most of these RPi fixes could/should probably be upstreamed

# there's PACKAGECONFIG options defined for this but no auto-detection
PACKAGECONFIG += "raspberrypi"

# simply missing from the recipe
DEPENDS += "libxkbcommon"

# something in the cmake/ninja magic is stripping before do_install
INSANE_SKIP:${PN} += "already-stripped"
WARN_QA:remove = "already-stripped compile-host-path"

# use my own kodi.service from kodi-autostart.bb
# TODO: merge that with the kodi recipe here
SYSTEMD_SERVICE:${PN} = ""
do_install:append() {
    # kodi_18.bb hard-codes /lib/systemd instead of using systemd_unitdir, which
    # is bad for using usrmerge
    rm -rf ${D}/lib/systemd ${D}${systemd_unitdir}
    if [ -z "$(find ${D}/lib -not -type d)" ]; then
        rm -rf ${D}/lib
    fi
}
