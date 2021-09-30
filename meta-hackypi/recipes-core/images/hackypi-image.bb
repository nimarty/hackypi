SUMMARY = "hackypi image with vulnerabilities to exploit"
IMAGE_LINGUAS = " "
LICENSE = "MIT"

inherit core-image extrausers

# enable sd card image build
IMAGE_FSTYPES = "tar.xz ext3 rpi-sdimg"

# enable uart
ENABLE_UART= "1"

# from core-minimal-image
IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"

# add features, packages and users
EXTRA_IMAGE_FEATURES = "\
	ssh-server-dropbear \
	package-management \
	"

IMAGE_INSTALL = " \
	packagegroup-core-boot \
	dhcpcd \
	opkg \
	${CORE_IMAGE_EXTRA_INSTALL} \
	"

EXTRA_USERS_PARAMS = "\
	usermod -p '\$5\$qV9csjHmjb74QXWC\$TzaiyMYYAeQqJTd1/kESezOXT.1huQxwtx3DVhbtYJC' root; \
	"
#	useradd -u 1200 -m -d /home/hacky -r -s /bin/sh -p '\$1\$wixRgy/g\$RKBIS5HFghq4CNu3ykD2j1' hacky; \
#	groupadd -g 880 hackygroup; \
#	usermod -a -G hackygroup hacky; \
