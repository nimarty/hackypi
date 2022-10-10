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

#do_compile () {
#    # TODO
#    #mkdir ${WORKDIR}/build-memlog
#    #cmake -S ./src -B ${WORKDIR}/build-memlog
#    #cmake --build ${WORKDIR}/build-memlog
#}

#do_install () {
#    # install application
#    #install -d ${D}${base_prefix}/opt/webserver/
#    #install -m 0755 ${WORKDIR}/webserver.py ${D}${base_prefix}/opt/webserver/
#
#    # install init.d service
#    #install -d ${D}${base_prefix}${sysconfdir}/init.d/
#    #install -m 0755 ${WORKDIR}/webserver ${D}${base_prefix}${sysconfdir}/init.d/
#}

#pkg_postinst_${PN} () {
#    #useradd webserveruser
#    #chmod a+r /etc/shadow
#    #useradd -p \$1\$5sGNjVOx\$uUu/JAD6cZx/gcMoHt5bb. hacky
#    #echo 'the_secret' > /home/hacky/treasure
#    #chown hacky /home/hacky/treasure
#    #chmod 600 /home/hacky/treasure
#    #echo 'AllowUsers hacky' >> /etc/ssh/sshd_config
#    #/etc/init.d/sshd restart
#}

#pkg_postrm_${PN} () {
#    #chmod 400 /etc/shadow
#    #userdel -f webserveruser
#    #rm -rf /home/webserveruser
#    #userdel -f hacky
#    #rm -rf /home/hacky
#    #sed -i '/AllowUsers hacky/d' /etc/ssh/sshd_config
#    #/etc/init.d/sshd restart
#}

RDEPENDS_${PN} = " \
    "

FILES_${PN} = " \
    ${base_prefix}/usr/bin/* \
    ${base_prefix}/usr/lib/* \
    ${base_prefix}${sysconfdir}/init.d/* \
    "
