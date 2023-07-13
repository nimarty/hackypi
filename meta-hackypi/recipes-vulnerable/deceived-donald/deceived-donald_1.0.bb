LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

inherit pkgconfig systemd cmake

SRC_URI = " \
    file://memlog.service \
    file://src/ \
    "

S = "${WORKDIR}/src"

do_install () {
    # install application
    cmake_do_install

    # install systemd service
    install -d ${D}/${systemd_unitdir}/system
    install -m 0755 ${WORKDIR}/memlog.service ${D}/${systemd_unitdir}/system

    # modify access rights to lib
    chmod 777 ${D}${base_prefix}/usr/lib/libmemfunctions.so.1.0.0
}

pkg_postinst:${PN} () {
    #password is "hacky", created with command "openssl passwd"
    useradd -p '$1$IebNOasl$pmPilB8C2b3wuax1tkha7/' donald
    printf 'It does not matter how slowly you go so long as you do not stop.\n - Confucius\n' > /home/donald/treasure
    chown root /home/donald/treasure
    chmod 600 /home/donald/treasure
    echo 'AllowUsers donald' >> /etc/ssh/sshd_config
    /etc/init.d/sshd restart
    systemctl daemon-reload
    systemctl start memlog
}

pkg_postrm:${PN} () {
    userdel -f donald
    rm -rf /home/donald
    sed -i '/AllowUsers donald/d' /etc/ssh/sshd_config
    /etc/init.d/sshd restart
    systemctl daemon-reload
    systemctl stop memlog
    rm -f /lib/systemd/system/memlog.service
    systemctl daemon-reload
}

RDEPENDS:${PN} = " \
    ldd \
    "

FILES:${PN} = " \
    ${base_prefix}/usr/bin/* \
    ${base_prefix}/usr/lib/* \
    ${systemd_unitdir}/system/* \
    "
