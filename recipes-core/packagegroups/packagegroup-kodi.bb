SUMMARY = "Kodi Media Center Packagegroup"

inherit packagegroup

RDEPENDS_${PN} = " \
    kodi \
    kodi-send \
    kodi-autostart \
    libkodiplatform \
    userland \
"
