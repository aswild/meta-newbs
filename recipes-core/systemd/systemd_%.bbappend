# enable systemd-networkd
PACKAGECONFIG += "networkd resolved"

# enable zsh completion
RDEPENDS_${PN} += "${PN}-zsh-completion"
