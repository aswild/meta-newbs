SUMMARY = "Kodi Media Center Packagegroup"

inherit packagegroup

RDEPENDS_${PN} = " \
    kodi \
    kodi-autostart \
    libkodiplatform \
    userland \
"
