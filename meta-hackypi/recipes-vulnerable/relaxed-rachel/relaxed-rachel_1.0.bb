SUMMARY = "Relaxed Rachel challenge"
DESCRIPTION = "Package installing a simple FTP server"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

inherit pkgconfig systemd

SRC_URI = " \
    file://ftpserver.service \
    "

RDEPENDS:${PN} = " \
    pure-ftpd \
    "

do_install() {
    # install systemd service
    install -d ${D}/${systemd_unitdir}/system
    install -m 0755 ${WORKDIR}/ftpserver.service ${D}/${systemd_unitdir}/system
}

pkg_postinst:${PN}() {
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
    systemctl daemon-reload
    systemctl start ftpserver
}

pkg_postrm:${PN}() {
    # remove user and its files
    userdel -fr rachel
    groupdel -f ftp
    systemctl daemon-reload
    systemctl stop ftpserver
    rm -f /lib/systemd/system/ftpserver.service
    systemctl daemon-reload
}

FILES:${PN} = " \
    ${systemd_unitdir}/system/* \
    "
