SUMMARY = "hackypi image with vulnerabilities to exploit"

inherit core-image

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

LICENSE = "MIT"



IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"

EXTRA_IMAGE_FEATURES = " \
	ssh-server-dropbear \
"

IMAGE_INSTALL += "dhcpcd"
IMAGE_INSTALL += "useradd"
