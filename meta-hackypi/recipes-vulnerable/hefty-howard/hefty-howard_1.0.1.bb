LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

inherit pkgconfig

SRC_URI = " \
    file://disconnected.php \
    file://disconnect.php \
    file://index.html \
    file://index.php \
    file://picture.png \
    file://spacer.png \
    file://title.png \
    file://robots.txt \
    "

do_install () {
    # install web-application
    install -d ${D}${base_prefix}//usr/share/apache2/htdocs
    install ${WORKDIR}/disconnect.php ${D}${base_prefix}/usr/share/apache2/htdocs
    install ${WORKDIR}/disconnected.php ${D}${base_prefix}/usr/share/apache2/htdocs
    install ${WORKDIR}/index.html ${D}${base_prefix}/usr/share/apache2/htdocs
    install ${WORKDIR}/index.php ${D}${base_prefix}/usr/share/apache2/htdocs
    install ${WORKDIR}/picture.png ${D}${base_prefix}/usr/share/apache2/htdocs
    install ${WORKDIR}/spacer.png ${D}${base_prefix}/usr/share/apache2/htdocs
    install ${WORKDIR}/title.png ${D}${base_prefix}/usr/share/apache2/htdocs
    install ${WORKDIR}/robots.txt ${D}${base_prefix}/usr/share/apache2/htdocs
}

pkg_postinst_${PN} () {
    sed -i "s/apache2\/default-site\/htdocs/apache2\/htdocs/" /etc/apache2/httpd.conf
    /etc/init.d/apache2 reload
    
    useradd -p "\$6\$HUpwgjNWFh9bIDK\$DYpDI7MWK9Rf2fWKzMQzYieqGJWrTDWnOLr.zRpOkhwbpxycIRjy/G5NNnwhZOjxZsw7Wd2KYOj7.hdDKpqPG0" service
    sed -i "s/AllowUsers root/AllowUsers root service/" /etc/ssh/sshd_config
    /etc/init.d/sshd restart
    
    chmod u+s /usr/bin/hems_cli
    
    echo "kernel.randomize_va_space = 0" >> /etc/sysctl.conf
    sysctl -p /etc/sysctl.conf
}

pkg_postrm_${PN} () {
    sed -i "s/apache2\/htdocs/apache2\/default-site\/htdocs/" /etc/apache2/httpd.conf
    /etc/init.d/apache2 reload
    
    userdel -r service
    sed -i "s/AllowUsers root service/AllowUsers root/" /etc/ssh/sshd_config
    /etc/init.d/sshd restart

    sed -i "s/kernel.randomize_va_space = 0//" /etc/sysctl.conf
    sysctl -p /etc/sysctl.conf
}

RDEPENDS_${PN} = " \
    apache2 \
    hefty-howard-cli \
    php-fpm \
    php-fpm-apache2 \
    "

FILES_${PN} = " \
    ${base_prefix}/usr/share/apache2/htdocs/* \
    "
