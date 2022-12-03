# class for fetching source from the local workspace

NEWBS_SRCDIR ?= "${NEWBSROOT}/newbs-source"
NEWBS_SRCNAME ?= "YOU_MUST_SET_NEWBS_SRCNAME"

NEWBS_SRCDIR_DIR = "${@os.path.dirname(d.getVar('NEWBS_SRCDIR'))}"
NEWBS_SRCDIR_BASE = "${@os.path.basename(d.getVar('NEWBS_SRCDIR'))}"

FILESEXTRAPATHS:prepend = "${NEWBS_SRCDIR_DIR}:"
SRC_URI = "file://${NEWBS_SRCDIR_BASE}/${NEWBS_SRCNAME}"
SRCREV = "${AUTOREV}"
PV:append = "+newbslocal"
S = "${WORKDIR}/${NEWBS_SRCDIR_BASE}/${NEWBS_SRCNAME}"
