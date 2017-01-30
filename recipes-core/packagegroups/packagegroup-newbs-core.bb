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
    e2fsprogs \
    git \
    htop \
    ifupdown \
    iproute2 \
    iputils \
    newbs-nvram \
    openssh \
    openssh-sshd \
    openssh-sftp \
    openssh-sftp-server \
    rsync \
    sysstat \
    the-silver-searcher \
    tmux \
    usbutils \
    vim \
    wget \
    wild-linuxfiles \
    zsh \
"

#RDEPENDS_${PN} += "packagegroup-core-buildessential"

#packagegroup-core-full-cmdline-initscripts
#xfsprogs
