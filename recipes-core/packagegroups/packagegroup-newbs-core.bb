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
    e2fsprogs-mke2fs \
    e2fsprogs-resize2fs \
    e2fsprogs-tune2fs \
    git \
    git-perltools \
    htop \
    i2c-tools \
    iproute2 \
    iputils \
    make \
    man \
    newbs-nvram \
    openssh \
    openssh-sshd \
    openssh-sftp \
    openssh-sftp-server \
    python \
    python-modules \
    python3 \
    python3-modules \
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
