do_install_append(){
    # allow root user login via ssh
    echo 'PermitRootLogin yes' >> ${D}${sysconfdir}/ssh/sshd_config
    echo 'AllowUsers root' >> ${D}${sysconfdir}/ssh/sshd_config
}
