LINUX_VERSION = "6.12.93"
SRCREV = "3f2dce129bcf7e6967e90bd46844174a456f7e68"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=https;branch=rpi-6.12.y"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
SRC_URI += "file://0001-drm-v3d-remove-duplicate-v3d_cpu_job_free.patch"

require linux-raspberrypi-newbs.inc
