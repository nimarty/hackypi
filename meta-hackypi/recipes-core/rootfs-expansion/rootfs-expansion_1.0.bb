SUMMARY = "RootFS Expansion"
DESCRIPTION = "Expands root filesystem to available space on SD card"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

ALLOW_EMPTY:${PN} = "1"

RDEPENDS:${PN} = " \
    e2fsprogs-resize2fs \
    parted \
    "

pkg_postinst_ontarget:${PN}() {
    #!/bin/sh
    {
        # delete 2nd partition
        echo "d";
        echo "2";

        # create 2nd partition with full size
        echo "n";
        echo "p";
        echo "2";
        echo "106496"; # start block after boot partition
        echo ""; # last available block by default

        echo "w"; # write new table
    } | fdisk /dev/mmcblk0 || true # suppress warning that stops script immediately

    partprobe
    resize2fs /dev/mmcblk0p2

    exit 0
}
