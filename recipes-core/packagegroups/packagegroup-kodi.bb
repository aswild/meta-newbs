SUMMARY = "Kodi Media Center Packagegroup"

inherit packagegroup

RDEPENDS_${PN} = " \
    kodi \
    kodi-autostart \
    libkodiplatform \
    ${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', 'userland-nogl', 'userland', d)} \
"
