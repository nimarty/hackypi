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

pkg_postinst:${PN} () {
    # Add new user with name 'service'
    useradd -p "\$6\$HUpwgjNWFh9bIDK\$DYpDI7MWK9Rf2fWKzMQzYieqGJWrTDWnOLr.zRpOkhwbpxycIRjy/G5NNnwhZOjxZsw7Wd2KYOj7.hdDKpqPG0" service

    # And allow ssh login for user 'service'
    sed -i "s/AllowUsers root/AllowUsers root service/" /etc/ssh/sshd_config
    /etc/init.d/sshd restart
    
    # Set the sticky bit for the HEMS CLI to allow user 'service' to execute 
    # it as root
    chmod u+s /usr/bin/hems_cli
    
    # Ease the buffer overflow by turning off ASLR
    echo "kernel.randomize_va_space = 0" >> /etc/sysctl.conf
    sysctl -p /etc/sysctl.conf

    # Set the PIN for the HEMS tools
    echo 3455 > /etc/hems
    chmod 640 /etc/hems
    chown nobody:nogroup /etc/hems

    # Set the flag to be read by the exploit
    echo 4711 > /etc/flag
    chmod 640 /etc/flag
}

pkg_postrm:${PN} () {
    rm /etc/flag

    rm /etc/hems

    sed -i "/kernel.randomize_va_space = 0/d" /etc/sysctl.conf
    sysctl -p /etc/sysctl.conf

    sed -i "s/AllowUsers root service/AllowUsers root/" /etc/ssh/sshd_config
    /etc/init.d/sshd restart

    userdel -r service
}

RDEPENDS:${PN} = " \
    gdb \
    "
