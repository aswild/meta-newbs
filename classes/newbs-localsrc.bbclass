# class for fetching source from the local workspace

NEWBS_SRCDIR ?= "${NEWBSROOT}/newbs-source"
NEWBS_SRCNAME ?= "YOU_MUST_SET_NEWBS_SRCNAME"

FILESEXTRAPATHS_prepend = "${NEWBS_SRCDIR}:"
SRC_URI = "file://${NEWBS_SRCNAME}"
SRCREV = "${AUTOREV}"
PV_append = "+newbslocal"
S = "${WORKDIR}/${NEWBS_SRCNAME}"
