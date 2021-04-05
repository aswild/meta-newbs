SUMMARY = "Borg Backup"
DESCRIPTION = "Deduplicating backup program with compression and authenticated encryption"
HOMEPAGE = "https://borg.bauerj.eu"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a8e043b87e94a013793baa08b364c941"

PROVIDES = "borgbackup"
RPROVIDES_${PN} = "borgbackup"

BORG_ARCH = "unsupported_arch"
BORG_ARCH_aarch64 = "arm64"
SRCNAME = "borg-${PV}-${BORG_ARCH}"

PV = "1.1.16"
SRC_URI = "https://borg.bauerj.eu/bin/${SRCNAME};name=${BORG_ARCH} \
           file://LICENSE"
SRC_URI[arm64.sha256sum] = "9b1f60c38e8903bcb7d1a0c852b80c3d998c8349b24ee764cd560c89ffba4c95"

S = "${WORKDIR}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -Dm755 ${SRCNAME} ${D}${bindir}/borg
}

INSANE_SKIP_${PN} += "already-stripped"
RDEPENDS_${PN} = "libxcrypt-compat zlib"
