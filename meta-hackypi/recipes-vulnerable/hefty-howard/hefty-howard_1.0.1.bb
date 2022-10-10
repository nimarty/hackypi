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
}

pkg_postrm_${PN} () {
    sed -i "s/apache2\/htdocs/apache2\/default-site\/htdocs/" /etc/apache2/httpd.conf
    /etc/init.d/apache2 reload
}

RDEPENDS_${PN} = " \
    apache2 \
    php-fpm \
    php-fpm-apache2 \
    "

FILES_${PN} = " \
    ${base_prefix}/usr/share/apache2/htdocs/* \
    "
