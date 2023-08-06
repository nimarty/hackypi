LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

inherit pkgconfig systemd

SRC_URI = " \
    file://webserver.py \
    file://webserver.service \
    "

do_install () {
    # install application
    install -d ${D}${base_prefix}/opt/webserver/
    install -m 0755 ${WORKDIR}/webserver.py ${D}${base_prefix}/opt/webserver/

    # install systemd service
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/webserver.service ${D}/${systemd_unitdir}/system
}

pkg_postinst:${PN} () {
    useradd webserveruser
    chmod a+r /etc/shadow
    useradd -p \$1\$5sGNjVOx\$uUu/JAD6cZx/gcMoHt5bb. hacky
    echo 'the_secret' > /home/hacky/treasure
    chown hacky /home/hacky/treasure
    chmod 600 /home/hacky/treasure
    echo 'AllowUsers hacky' >> /etc/ssh/sshd_config
    /etc/init.d/sshd restart
    systemctl daemon-reload
    systemctl start webserver
}

pkg_postrm:${PN} () {
    chmod 400 /etc/shadow
    userdel -f webserveruser
    rm -rf /home/webserveruser
    userdel -f hacky
    rm -rf /home/hacky
    sed -i '/AllowUsers hacky/d' /etc/ssh/sshd_config
    /etc/init.d/sshd restart
    systemctl daemon-reload
    systemctl stop webserver
    rm -f /lib/systemd/system/webserver.service
    systemctl daemon-reload
}

RDEPENDS:${PN} = " \
    python3-flask \
    python3-flask-restful \
    python3-six \
    "

FILES:${PN} = " \
    ${base_prefix}/opt/webserver/* \
    ${systemd_unitdir}/system/* \
    "
