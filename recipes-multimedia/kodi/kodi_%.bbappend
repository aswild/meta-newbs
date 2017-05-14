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
EXTRA_OEMAKE = "V=1"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
    file://1000-raspberrypi-autoconf-fixes.patch \
"
