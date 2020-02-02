require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

SRC_URI[md5sum] = "0a78b1dc48dc032fe505b170c1b92339"
SRC_URI[sha256sum] = "a6fbd4ee903c128367892c2393ee0d9657b6ed3ea90016d4dc6f1f6da20b2330"

# new kernel uses rsync as part of headers_install
DEPENDS += "rsync-native"
