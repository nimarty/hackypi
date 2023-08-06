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
    install -d ${D}${base_prefix}/usr/share/apache2/htdocs
    install -m 0644 ${WORKDIR}/disconnect.php ${D}${base_prefix}/usr/share/apache2/htdocs
    install -m 0644 ${WORKDIR}/disconnected.php ${D}${base_prefix}/usr/share/apache2/htdocs
    install -m 0644 ${WORKDIR}/index.html ${D}${base_prefix}/usr/share/apache2/htdocs
    install -m 0644 ${WORKDIR}/index.php ${D}${base_prefix}/usr/share/apache2/htdocs
    install -m 0644 ${WORKDIR}/picture.png ${D}${base_prefix}/usr/share/apache2/htdocs
    install -m 0644 ${WORKDIR}/spacer.png ${D}${base_prefix}/usr/share/apache2/htdocs
    install -m 0644 ${WORKDIR}/title.png ${D}${base_prefix}/usr/share/apache2/htdocs
    install -m 0644 ${WORKDIR}/robots.txt ${D}${base_prefix}/usr/share/apache2/htdocs
}

pkg_postinst:${PN} () {
    sed -i "s/apache2\/default-site\/htdocs/apache2\/htdocs/" /etc/apache2/httpd.conf
    /etc/init.d/apache2 reload
}

pkg_postrm:${PN} () {
    sed -i "s/apache2\/htdocs/apache2\/default-site\/htdocs/" /etc/apache2/httpd.conf
    /etc/init.d/apache2 reload
}

RDEPENDS:${PN} = " \
    apache2 \
    hefty-howard-cli \
    php-fpm \
    php-fpm-apache2 \
    "

FILES:${PN} = " \
    ${base_prefix}/usr/share/apache2/htdocs/* \
    "
