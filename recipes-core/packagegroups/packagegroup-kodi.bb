SUMMARY = "Kodi Media Center Packagegroup"

inherit packagegroup

RDEPENDS:${PN} = " \
    kodi \
    kodi-autostart \
    libkodiplatform \
"
