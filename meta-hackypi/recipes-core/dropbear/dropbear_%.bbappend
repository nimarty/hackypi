do_install_append(){
    # allow root user login via ssh
    sed -i 's/-w//g' ${D}/etc/default/dropbear
}
