# image class to build nImage images for newbs-swdl.
# nImage depends on newbs-bootimg and pulls it in automatically.
# nImages are built for each entry of IMAGE_FSTYPES that matches
# the regex NIMG_FSMATCH

inherit image_types newbs-bootimg

NIMG_FSMATCH ?= "ext\d|squashfs.*"
NIMG_FSTYPES = "${@' '.join(nimg_fstypes(d))}"

def nimg_fstypes(d):
    # returns a generator for all the nimage types to build
    import re
    nimg_fsmatch = re.compile(d.getVar('NIMG_FSMATCH'))
    fstypes = d.getVar('IMAGE_FSTYPES').split()
    return (t for t in fstypes if nimg_fsmatch.match(t))

do_image_nimage[depends] += " \
    mknimage-native:do_populate_sysroot \
    zstd-native:do_populate_sysroot \
    ${PN}:do_image_newbs_bootimg \
    ${@' '.join('${PN}:do_image_%s'%t.replace('-', '_') for t in nimg_fstypes(d))} \
"

rootfs_ptype() {
    # shell function to translate Yocto image fstype to nImage part type
    case $1 in
        ext*) ptype=rootfs_rw ;;
        *)    ptype=rootfs ;;
    esac
    echo $ptype
}

IMAGE_CMD:nimage() {
    bootimg_name="$(basename ${DEPLOY_BOOTIMG_SYMLINK})"

    set -x
    bbnote "Compressing $bootimg_name"
    rm -f ${WORKDIR}/$bootimg_name
    for rootfstype in ${NIMG_FSTYPES}; do
        nimg_name="${IMAGE_NAME}.$rootfstype.nimg"
        bbnote "Creating nImage $nimg_name"
        mknImage create -o ${IMGDEPLOYDIR}/$nimg_name \
                        -a -n ${IMAGE_NAME}.$rootfstype \
                        boot_img_zstd:${DEPLOY_BOOTIMG_SYMLINK} \
                        $(rootfs_ptype $rootfstype):${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.$rootfstype

        ln -svfT $nimg_name ${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.$rootfstype.nimg
    done
}
