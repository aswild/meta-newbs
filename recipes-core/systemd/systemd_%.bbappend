# enable systemd-networkd
PACKAGECONFIG_append = " networkd resolved"

# enable zsh completion
RDEPENDS_${PN} += "${PN}-zsh-completion"
