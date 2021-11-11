SUMMARY = "hackypi image with vulnerabilities to exploit"
IMAGE_LINGUAS = " "
LICENSE = "MIT"

inherit core-image extrausers

# enable sd card image build
IMAGE_FSTYPES = "tar.xz ext3 rpi-sdimg"

# from core-minimal-image
IMAGE_ROOTFS_SIZE ?= "8192"

# add features, packages and users
EXTRA_IMAGE_FEATURES = " \
    ssh-server-openssh \
    package-management \
    "

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    dhcpcd \
    opkg \
    rootfs-expansion \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

EXTRA_USERS_PARAMS = " \
    usermod -p '\$5\$qV9csjHmjb74QXWC\$TzaiyMYYAeQqJTd1/kESezOXT.1huQxwtx3DVhbtYJC' root; \
    "
