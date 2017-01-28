DESCRIPTION = "NEWBS Recovery Root FS packagegroup"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    packagegroup-core-full-cmdline-libs \
    packagegroup-core-full-cmdline-utils \
    packagegroup-core-full-cmdline-sys-services \
    dosfstools \
    git \
    htop \
    iproute2 \
    iputils \
    partclone \
    the-silver-searcher \
    tmux \
    usbutils \
    vim \
    wild-linuxfiles \
    zsh \
"

#packagegroup-core-full-cmdline-initscripts
