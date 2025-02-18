DESCRIPTION = "Sample program to read the serial number from VCHI"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://pi-serial-number.c;endline=20;md5=856230a0b6476c6a466d75c76e25ca5b"

COMPATIBLE_MACHINE = "^rpi$"
DEPENDS = "userland"

SRC_URI = "file://pi-serial-number.c"
S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

do_compile() {
    ${CCLD} ${CFLAGS} -Wall -Werror ${LDFLAGS} -o pi-serial-number pi-serial-number.c -lvcos -lvchiq:arm -lvchostif
}

do_install() {
    install -Dm755 pi-serial-number ${D}${bindir}/pi-serial-number
}
