SUMMARY = "Borg Backup"
DESCRIPTION = "Deduplicating backup program with compression and authenticated encryption"
HOMEPAGE = "https://borg.bauerj.eu"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a8e043b87e94a013793baa08b364c941"

PROVIDES = "borgbackup"
RPROVIDES:${PN} = "borgbackup"

BORG_ARCH = "unsupported_arch"
BORG_ARCH:aarch64 = "arm64"
SRCNAME = "borg-${PV}-${BORG_ARCH}"

PV = "1.2.6"
SRC_URI = "https://borg.bauerj.eu/bin/${SRCNAME};name=${BORG_ARCH} \
           file://LICENSE"
SRC_URI[arm64.sha256sum] = "127e24e4e41a27db5305cad86fef0ff43bb869b76b23afad0521fcdf4076efa5"

S = "${WORKDIR}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -Dm755 ${SRCNAME} ${D}${bindir}/borg
}

INSANE_SKIP:${PN} += "already-stripped"
RDEPENDS:${PN} = "libxcrypt-compat zlib"
