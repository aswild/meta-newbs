# userland-nographics_git.bb: a version of meta-raspberrypi's userland recipe
# with the GLES/EGL stuff removed, providing only the firmware interface binaries
# e.g. vcgencmd and the associated libraries.
# This is mainly for use on aarch64 builds where vc4graphics sets the preferred
# providers for virtual/libgles2 and virtual/egl are mesa rather than userland.

# main recipe from meta-raspberrypi
require recipes-graphics/userland/userland_git.bb

# find meta-raspberrypi patches, kind of a hack
FILESEXTRAPATHS_prepend = "${NEWBSROOT}/meta-raspberrypi/recipes-graphics/userland/userland:"

# replace the original userland package
CONFLICTS_append = " userland"
PROVIDES_append = " virtual/userland"
RPROVIDES_${PN}_append = " userland"

# remove graphics stuff
PROVIDES_remove = "virtual/libgles2 virtual/egl"
RPROVIDES_${PN}_remove = "libgles2 egl libegl"
PACKAGECONFIG_remove = "wayland"

# split vcfiled into its own package, not sure what it's for
PACKAGES =+ "${PN}-vcfiled"
FILES_${PN}-vcfiled = "${sysconfdir}/init.d/vcfiled \
                       ${sbindir}/vcfiled \
                       ${datadir}/install/vcfiled"

do_install_append() {
    bbnote "removing egl/gles headers"
    rm -rf ${D}${includedir}/{ELG,GLES,IL}

    bbnote "removing egl/gles/mmal pkgconfig files"
    rm -f ${D}${libdir}/pkgconfig/{egl,glesv2,mmal}.pc

    bbnote "removing example code"
    rm -rf ${D}${prefix}/src
}
