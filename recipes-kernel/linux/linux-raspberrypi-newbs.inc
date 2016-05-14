# NEWBS kernel customizations

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
            file://remove-networking.cfg \
           "

# default Linux recipes copy dtb blobs to DEPLOYDIR, which clutters it
# archive them all to a subdirectory named DTB_DEPLOYDIR
DTB_DEPLOYDIR ?= "dtb"

do_move_dtbs() {
    cd ${DEPLOY_DIR_IMAGE}
    install -d ${DTB_DEPLOYDIR}
    for d in *.dtb; do
        echo "archiving $d"
        mv $d ${DTB_DEPLOYDIR}/$d
    done
}

addtask move_dtbs after do_deploy
