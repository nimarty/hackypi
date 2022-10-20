SUMMARY = "Command line tool to manage HEMS from the terminal"
LICENSE = "CLOSED"
LIC_FILE_CHMSUM = ""

SRC_URI = "\
	file://CMakeLists.txt \
	file://main.cpp \
	"

S = "${WORKDIR}"

inherit cmake pkgconfig

EXTRA_OECMAKE = ""

pkg_postinst_${PN} () {
    useradd -p "\$6\$HUpwgjNWFh9bIDK\$DYpDI7MWK9Rf2fWKzMQzYieqGJWrTDWnOLr.zRpOkhwbpxycIRjy/G5NNnwhZOjxZsw7Wd2KYOj7.hdDKpqPG0" service
    sed -i "s/AllowUsers root/AllowUsers root service/" /etc/ssh/sshd_config
    /etc/init.d/sshd restart
    
    chmod u+s /usr/bin/hems_cli
    
    echo "kernel.randomize_va_space = 0" >> /etc/sysctl.conf
    sysctl -p /etc/sysctl.conf

    echo 4711 > /etc/flag
    chmod 640 /etc/flag
}

pkg_postrm_${PN} () {
    userdel -r service
    sed -i "s/AllowUsers root service/AllowUsers root/" /etc/ssh/sshd_config
    /etc/init.d/sshd restart

    sed -i "s/kernel.randomize_va_space = 0//" /etc/sysctl.conf
    sysctl -p /etc/sysctl.conf

    rm /etc/flag
}

RDEPENDS_${PN} = " \
    gdb \
    "
