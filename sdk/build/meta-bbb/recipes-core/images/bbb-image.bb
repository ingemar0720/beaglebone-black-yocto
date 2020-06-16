SUMMARY = "test build BBB image"
LICENSE = "MIT"
IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL} kernel-modules openssh docker-ce bash ntp init-ifupdown"
IMAGE_LINGUAS = " "
LICENSE = "MIT"
inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"
