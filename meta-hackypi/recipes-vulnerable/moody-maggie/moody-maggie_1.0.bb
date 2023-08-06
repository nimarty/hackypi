SUMMARY = "Moody Maggie challenge"
DESCRIPTION = "Easy to crack SSH user with mysterious access to root shell"
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

RDEPENDS:${PN} = "netcat"

inherit cmake systemd

SRC_URI = " \
    file://src/ \
    file://remote-shell.service \
    file://linpeas.sh \
"

S = "${WORKDIR}/src"

FILES:${PN} += " \
    /home/admin \
    ${systemd_unitdir}/system/* \
"

do_install () {
    install -d ${D}${bindir}
    install -m 0755 moody-maggie ${D}${bindir}/

    # install systemd service
    install -d ${D}/${systemd_unitdir}/system
    install -m 0755 ${WORKDIR}/remote-shell.service ${D}/${systemd_unitdir}/system

    install -d ${D}/home/admin
    install -m 0755 ${WORKDIR}/linpeas.sh ${D}/home/admin/
}

pkg_postinst:${PN} () {
    touch /home/root/mood
    echo "My mood is horrible, when you discover this!" >> /home/root/mood
    useradd -p \$1\$IrakJkPj\$.7awDdMyvrk1wqCXe9Zlx. admin
    echo 'AllowUsers admin' >> /etc/ssh/sshd_config
    /etc/init.d/sshd restart
    systemctl daemon-reload
    systemctl start remote-shell
    systemctl enable remote-shell
}

pkg_postrm:${PN} () {
    rm -f /home/root/mood
    userdel -fr admin
    sed -i '/AllowUsers admin/d' /etc/ssh/sshd_config
    /etc/init.d/sshd restart
    systemctl daemon-reload
    systemctl stop remote-shell
    systemctl disable remote-shell
    rm -f /lib/systemd/system/remote-shell.service
    systemctl daemon-reload
    rm -f /usr/bin/moody-maggie
}
