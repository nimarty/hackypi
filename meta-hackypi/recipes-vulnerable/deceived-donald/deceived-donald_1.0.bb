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
}

pkg_postinst_${PN} () {

}

pkg_postrm_${PN} () {

}

RDEPENDS_${PN} = " \
    "

FILES_${PN} = " \
    ${base_prefix}/usr/bin/* \
    ${base_prefix}/usr/lib/* \
    ${base_prefix}${sysconfdir}/init.d/* \
    "
