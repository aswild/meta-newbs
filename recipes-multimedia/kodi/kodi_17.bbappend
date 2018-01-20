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
    file://1002-ftpparse-use-std-string.patch \
    file://1003-crossguid-fetch-tarball-with-yocto.patch \
    \
    http://mirrors.kodi.tv/build-deps/sources/crossguid-8f399e8bd4.tar.gz;name=crossguid;unpack=0 \
"
SRC_URI[crossguid.md5sum] = "7de3be575744da5f1098295485ef0741"
SRC_URI[crossguid.sha256sum] = "3d77d09a5df0de510aeeb940df4cb534787ddff3bb1828779753f5dfa1229d10"

do_configure_prepend() {
    rm -rf tools/depends/target/crossguid/native
    mkdir -p tools/depends/target/crossguid/native
    tar --strip-components=1 -xf ${WORKDIR}/crossguid-8f399e8bd4.tar.gz -C tools/depends/target/crossguid/native
}

inherit python-dir
do_install_append() {
    install -m755 -D ${S}/tools/EventClients/Clients/Kodi\ Send/kodi-send.py \
                     ${D}${bindir}/kodi-send

    install -d ${D}${PYTHON_SITEPACKAGES_DIR}/kodi
    install -m644 ${S}/tools/EventClients/lib/python/__init__.py \
                  ${S}/tools/EventClients/lib/python/xbmcclient.py \
                  ${D}${PYTHON_SITEPACKAGES_DIR}/kodi/
}

PACKAGES =+ "${PN}-send"
FILES_${PN}-send = "${bindir}/kodi-send ${PYTHON_SITEPACKAGES_DIR}/kodi/*"
RDEPENDS_${PN}-send = "python python-io"
