# Recipe for Allen Wild's linuxfiles

DESCRIPTION = "Shell dotfiles"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=873a8e3c61b23b718b4594fa0a3e7449"

# gitsm:// fetcher clones submodules
SRC_URI = "gitsm://github.com/aswild/linuxfiles;branch=master"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
    file://htoprc \
"

# where on the rootfs to install to
LINUXFILES_LOC ?= "${ROOT_HOME}/.linuxfiles"

# force including all git data too
FILES_${PN} = "         \
    ${ROOT_HOME}        \
    ${LINUXFILES_LOC}   \
"
WARN_QA_remove = "file-rdeps"

do_install() {
    set -x
    installdir="${D}${LINUXFILES_LOC}"

    install -d $(dirname $installdir)
    cp -rvT ${S} $installdir

    cd $installdir
    git repack -ad
    rm -f .git/objects/info/alternates
    make DESTDIR="${D}${ROOT_HOME}" SRCDIR=${LINUXFILES_LOC} install

    # remove 'sudo' aliases
    sed -i '/alias.*sudo/d' $installdir/myshrc

    # create .bash_profile to source .bashrc
    echo '[[ -f $HOME/.bashrc ]] && . $HOME/.bashrc' >${D}${ROOT_HOME}/.bash_profile

    # copy htop config
    install -m 644 -D ${WORKDIR}/htoprc ${D}${ROOT_HOME}/.config/htoprc
}

do_configure[noexec] = "1"
do_compile[noexec] = "1"
