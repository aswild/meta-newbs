DESCRIPTION = "NEWBS Recovery Root FS packagegroup"
LICENSE = "MIT"

inherit packagegroup

PACKAGES += "${PN}-core"
RDEPENDS_${PN}-core = " \
    packagegroup-core-full-cmdline-libs \
    packagegroup-core-full-cmdline-utils \
    packagegroup-core-full-cmdline-sys-services \
    curl \
    dhcp-client \
    htop \
    iproute2 \
    iputils \
    linux-newbs-headers-dev \
    neostrip-demo \
    newbs-nvram \
    newbs-init-util \
    openssh \
    openssh-sshd \
    openssh-sftp \
    openssh-sftp-server \
    sysstat \
    the-silver-searcher \
    tmux \
    usbutils \
    vim \
    wget \
    wild-linuxfiles \
    zsh \
"

PACKAGES += "${PN}-devtools"
RDEPENDS_${PN}-devtools = " \
    make \
    python \
    python-modules \
    python3 \
    python3-modules \
    squashfs-tools \
"

PACKAGES += "${PN}-utils"
RDEPENDS_${PN}-utils = " \
    dosfstools \
    e2fsprogs \
    e2fsprogs-mke2fs \
    e2fsprogs-resize2fs \
    e2fsprogs-tune2fs \
    git \
    git-perltools \
    i2c-tools \
    ldd \
    rsync \
"

PACKAGES += "${PN}-full-cmdline"
RDEPENDS_${PN}-full-cmdline = " \
    ${PN}-core \
    ${PN}-devtools \
    ${PN}-utils \
"
