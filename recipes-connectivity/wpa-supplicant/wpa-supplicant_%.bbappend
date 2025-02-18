# enable wlan0 by default (iff /etc/wpa_supplicant/wpa_supplicant-wlan0.conf exists )
SYSTEMD_SERVICE:${PN} = "wpa_supplicant@wlan0.service"
SYSTEMD_AUTO_ENABLE = "enable"
