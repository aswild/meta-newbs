# from meta-openembedded/meta-oe
require recipes-support/libgpiod/libgpiod_1.4.5.bb

DEPENDS += "autoconf-archive-native"

SRC_URI[md5sum] = "dbcebfe6ff29ef4db33ed87ff7ff6d65"
SRC_URI[sha256sum] = "9549583cc897126c6a7c592f4020d5f522003562d9e43c8165336c698838da57"

SRC_URI += "file://0001-libgpiod-python-shared-only.patch"

# enable tools and python bindings
PACKAGECONFIG_append = " tools python3"
