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
    install -d ${D}${base_prefix}//usr/share/apache2/default-site/htdocs
    install ${WORKDIR}/disconnect.php ${D}${base_prefix}/usr/share/apache2/default-site/htdocs
    install ${WORKDIR}/disconnected.php ${D}${base_prefix}/usr/share/apache2/default-site/htdocs
    install ${WORKDIR}/index.html ${D}${base_prefix}/usr/share/apache2/default-site/htdocs
    install ${WORKDIR}/index.php ${D}${base_prefix}/usr/share/apache2/default-site/htdocs
    install ${WORKDIR}/picture.png ${D}${base_prefix}/usr/share/apache2/default-site/htdocs
    install ${WORKDIR}/spacer.png ${D}${base_prefix}/usr/share/apache2/default-site/htdocs
    install ${WORKDIR}/title.png ${D}${base_prefix}/usr/share/apache2/default-site/htdocs
    install ${WORKDIR}/robots.txt ${D}${base_prefix}/usr/share/apache2/default-site/htdocs
}

pkg_postinst_${PN} () {
}

pkg_postrm_${PN} () {
}

RDEPENDS_${PN} = " \
    apache2 \
    php-fpm \
    php-fpm-apache2 \
    "

FILES_${PN} = " \
    ${base_prefix}/usr/share/apache2/default-site/htdocs/* \
    "
