SUMMARY = "Moody Maggie challenge"
DESCRIPTION = "Easy to crack SSH user with mysterious access to root shell"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

RDEPENDS:${PN} = "netcat"

inherit update-rc.d

INITSCRIPT_NAME = "remote-shell.sh"
INITSCRIPT_PARAMS = "start 99 5 . stop 00 0 6 ."

SRC_URI = " \
    file://${INITSCRIPT_NAME} \
    file://linpeas.sh \
"

FILES:${PN} += " \
    /home/admin \
"

do_install () {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/${INITSCRIPT_NAME} ${D}${sysconfdir}/init.d/

    install -d ${D}/home/admin
    install -m 0755 ${WORKDIR}/linpeas.sh ${D}/home/admin/
}

pkg_postinst_${PN} () {
    touch /home/root/mood
    echo "My mood is horrible, when you discover this!" >> /home/root/mood
    useradd -p \$1\$IrakJkPj\$.7awDdMyvrk1wqCXe9Zlx. admin
    echo 'AllowUsers admin' >> /etc/ssh/sshd_config
    /etc/init.d/sshd restart
}

pkg_postrm_${PN} () {
    rm -f /home/root/mood
    userdel -fr admin
    sed -i '/AllowUsers admin/d' /etc/ssh/sshd_config
    /etc/init.d/sshd restart
}
