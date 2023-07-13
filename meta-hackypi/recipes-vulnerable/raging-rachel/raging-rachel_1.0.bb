SUMMARY = "Raging Rachel challenge"
DESCRIPTION = "Package installing a simple SFTP server"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit pkgconfig systemd

SRC_URI = " \
    file://file.txt \
    "

do_install () {
    install -d ${D}/home/rachel
    install -m 0755 ${WORKDIR}/file.txt ${D}/home/rachel/
}

pkg_postinst:${PN}() {

    # add new user and its files
    echo "[x] Add user files"
    groupadd -f ftp
    useradd -p '$6$hackypi123$kQfLoTMoKoxglxUZ.S6HbsmnIj/gb/MGNap/gjiW1d.XlVZOMaKkHH5p1FlMJXgAa2Z/XaRA7R6t9tURSSBBt/' \
        -g ftp rachel
    echo "And you'll see why 1984 won't be like 1984." > /home/rachel/file.txt
    mkdir -m 500 /home/rachel/treasure
    echo "1984 Apple ad" > /home/rachel/treasure/secret
    chown -R rachel:ftp /home/rachel/
    chmod 660 /home/rachel/file.txt
    chmod 400 /home/rachel/treasure/secret
    echo 'AllowUsers rachel' >> /etc/ssh/sshd_config
    /etc/init.d/sshd restart
}

pkg_postrm:${PN}() {
    # remove user and its files
    echo "[x] Delete user files"
    userdel -fr rachel
    groupdel -f ftp
    sed -i '/AllowUsers rachel/d' /etc/ssh/sshd_config
    /etc/init.d/sshd restart
}

FILES:${PN} += " \
    /home/rachel \
    "
