DESCRIPTION = "NEWBS Recovery Root FS packagegroup"
LICENSE = "MIT"

inherit packagegroup

RDEPENDS_${PN} = " \
    packagegroup-core-full-cmdline-libs \
    packagegroup-core-full-cmdline-utils \
    packagegroup-core-full-cmdline-sys-services \
    curl \
    dhcp-client \
    dosfstools \
    git \
    htop \
    iproute2 \
    iputils \
    openssh \
    openssh-sshd \
    partclone \
    sysstat \
    the-silver-searcher \
    tmux \
    usbutils \
    vim \
    wget \
    wild-linuxfiles \
    zsh \
"

RDEPENDS_${PN} += "packagegroup-core-buildessential"

#packagegroup-core-full-cmdline-initscripts
