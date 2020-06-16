SUMMARY = "test build RPI image"
LICENSE = "MIT"
IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL} kernel-modules openssh bash ntp docker-ce init-ifupdown linux-firmware-rpidistro-bcm43430 bluez5 i2c-tools python-smbus bridge-utils hostapd dhcp-server iptables wpa-supplicant golang-helloworld"
IMAGE_LINGUAS = " "
LICENSE = "MIT"
inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"
