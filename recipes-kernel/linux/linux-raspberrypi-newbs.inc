# NEWBS kernel customizations

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
            file://remove-networking.cfg \
           "
