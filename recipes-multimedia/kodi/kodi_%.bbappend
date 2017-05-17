# Fixes to get kodi to build

# Remove X dependencies
DEPENDS_remove = " \
    libxext \
    libxinerama \
    libxmu \
    libxrandr \
    libxtst \
    xdpyinfo \
    virtual/libgles1 \
"

RRECOMMENDS_${PN}_remove = " \
    tzdata-africa \
    tzdata-antarctica \
    tzdata-arctic \
    tzdata-asia \
    tzdata-atlantic \
    tzdata-austrailia \
    tzdata-europe \
    tzdata-pacific \
    xdpyinfo \
    xrandr \
"

DEPENDS += " \
    python-native \
    unzip-native \
"

PACKAGECONFIG = "openglesv2"
EXTRA_OECONF += "--with-platform=raspberry-pi2 --enable-player=omxplayer"
EXTRA_OEMAKE += "V=1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " \
    file://1000-raspberrypi-autoconf-fixes.patch \
    file://1001-graceful-shutdown-support.patch \
"

inherit python-dir
do_install_append() {
    install -m755 -D ${S}/tools/EventClients/Clients/Kodi\ Send/kodi-send.py \
                     ${D}${bindir}/kodi-send

    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/kodi
    install -m644 ${S}/tools/EventClients/lib/python/__init__.py \
                  ${S}/tools/EventClients/lib/python/xbmcclient.py \
                  ${D}${PYTHON_SITEPACKAGES_DIR}/kodi/
}

PACKAGES += "${PN}-send"
FILES_${PN}-send = "${bindir}/kodi-send ${PYTHON_SITEPACKAGES_DIR}/kodi/*"
RDEPENDS_${PN}-send = "python python-io"
