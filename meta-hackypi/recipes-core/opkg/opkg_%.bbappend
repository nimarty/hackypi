do_install_append(){
    echo 'src/gz hackypackages http://192.168.1.11:8080' >> ${D}${sysconfdir}/opkg/opkg.conf
}
