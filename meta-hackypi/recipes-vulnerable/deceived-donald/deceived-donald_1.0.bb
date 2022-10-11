LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

inherit pkgconfig update-rc.d cmake

SRC_URI = " \
    file://memlog \
    file://src/ \
    "

S = "${WORKDIR}/src"

INITSCRIPT_PARAMS = "start 02 2 3 4 5 . stop 01 0 1 6 ."
INITSCRIPT_NAME = "memlog"

do_install () {
    # install application
    cmake_do_install

    # install init.d service
    install -d ${D}${base_prefix}${sysconfdir}/init.d/
    install -m 0755 ${WORKDIR}/memlog ${D}${base_prefix}${sysconfdir}/init.d/

    # modify access rights to lib
    chmod 777 ${D}${base_prefix}/usr/lib/libmemfunctions.so.1.0.0
}

pkg_postinst_${PN} () {
    useradd -p '$1$IebNOasl$pmPilB8C2b3wuax1tkha7/' donald
    printf 'It does not matter how slowly you go so long as you do not stop.\n - Confucius\n' > /home/donald/treasure
    chown root /home/donald/treasure
    chmod 600 /home/donald/treasure
    echo 'AllowUsers donald' >> /etc/ssh/sshd_config
    /etc/init.d/sshd restart
}

pkg_postrm_${PN} () {
    userdel -f donald
    rm -rf /home/donald
    sed -i '/AllowUsers donald/d' /etc/ssh/sshd_config
    /etc/init.d/sshd restart
}

RDEPENDS_${PN} = " \
    ldd \
    "

FILES_${PN} = " \
    ${base_prefix}/usr/bin/* \
    ${base_prefix}/usr/lib/* \
    ${base_prefix}${sysconfdir}/init.d/* \
    "
