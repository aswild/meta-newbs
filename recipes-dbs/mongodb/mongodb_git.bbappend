# Pi 3 64-bit does support CRC instructions, and this layer adds
# -mcpu=cortex-a53+crc to TUNE_CCARGS, so remove the debian patch which disables them
SRC_URI_remove_raspberrypi3-64 = "file://disable-hw-crc32-on-arm64-s390x.patch"
