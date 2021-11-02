SUMMARY = "Relaxed Rachel challenge"
DESCRIPTION = "Package installing a simple FTP server"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

inherit pkgconfig update-rc.d

SRC_URI = " \
    file://ftpserver \
    "

INITSCRIPT_NAME = "ftpserver"
# INITSCRIPT_PARAMS = ""

RDEPENDS_${PN} = " \
    pure-ftpd \
    "

do_install() {
    install -d ${D}${base_prefix}${sysconfdir}/init.d/
    install -m 0755 ${WORKDIR}/ftpserver ${D}${base_prefix}${sysconfdir}/init.d/
}

pkg_postinst_${PN}() {
    # add new user and its files
    groupadd ftp
    useradd -p "\$6\$bfnJZ/LB\$6kMYSmH0qtx6ZJ6sR4cRBdjDNEOcf9X1ebF2kL6jM2KQusayRNcCXhNbYrio.LFYGUXIpA4n4TqpZPhc3PyVw1" \
        -g ftp rachel
    echo "Hack me if you can!" > /home/rachel/file.txt
    mkdir -m 500 /home/rachel/treasure
    echo "MY SECRET" > /home/rachel/treasure/secret
    chown -R rachel:ftp /home/rachel/
    chmod 660 /home/rachel/file.txt
    chmod 400 /home/rachel/treasure/secret
}

pkg_postrm_${PN}() {
    # remove user and its files
    userdel -fr rachel
    groupdel -f ftp
}

FILES_${PN} = " \
    ${base_prefix}${sysconfdir}/init.d/* \
    "
