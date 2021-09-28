SUMMARY = "hackypi image with vulnerabilities to exploit"

inherit core-image
inherit extrausers

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

LICENSE = "MIT"


IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"

EXTRA_IMAGE_FEATURES = " \
	ssh-server-dropbear \
"

IMAGE_INSTALL += " \
	dhcpcd \
	"

EXTRA_USERS_PARAMS = "\
	usermod -p '\$1\$1HfuVw8b\$TWHOrpnNLsyXteoXRLiPt0' root; \
	useradd -u 1200 -m -d /home/hacky -r -s /bin/sh -p '\$1\$wixRgy/g\$RKBIS5HFghq4CNu3ykD2j1' hacky; \
	groupadd -g 880 hackygroup; \
	usermod -a -G hackygroup hacky; \
	"

