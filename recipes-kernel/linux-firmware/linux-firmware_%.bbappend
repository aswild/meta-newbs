# Raspberry Pi 3 wifi needs the NVRAM text along with the firmware

NVRAM_GITREV = "54bab3d6a6d43239c71d26464e6e10e5067ffea7"
SRC_URI += "https://raw.githubusercontent.com/RPi-Distro/firmware-nonfree/${NVRAM_GITREV}/brcm80211/brcm/brcmfmac43430-sdio.txt;name=nvram-brcm43430"
SRC_URI[nvram-brcm43430.md5sum] = "8c3cb6d8f0609b43f09d083b4006ec5a"
SRC_URI[nvram-brcm43430.sha256sum] = "872fde4f9942d9aba805880d6eaddfe050305626fd58ad955bfe77c04f6b75a5"

FILES_${PN}-bcm43430_append = " /lib/firmware/brcm/brcmfmac43430-sdio.txt"

do_install_append() {
    install -m 0644 -D ${WORKDIR}/brcmfmac43430-sdio.txt ${D}/lib/firmware/brcm/
}
