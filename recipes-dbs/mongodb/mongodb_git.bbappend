# Pi 3 64-bit does support CRC instructions, and the cortexa53 tune uses
# -mcpu=cortex-a53+simd+crc, so remove the debian patch which disables them
SRC_URI_remove_raspberrypi3-64 = "file://disable-hw-crc32-on-arm64-s390x.patch"
