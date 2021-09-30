LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

inherit pkgconfig update-rc.d

SRC_URI = "\
	file://webserver.py \
	file://webserver \
	"

INITSCRIPT_PARAMS = "start 02 2 3 4 5 . stop 01 0 1 6 ."
INITSCRIPT_NAME = "webserver"

do_install () {
	# install application
	install -d ${D}${base_prefix}/opt/webserver/
	install -m 0755 ${WORKDIR}/webserver.py ${D}${base_prefix}/opt/webserver/

	# install init.d service
	install -d ${D}${base_prefix}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/webserver ${D}${base_prefix}${sysconfdir}/init.d/
}

pkg_postinst_${PN} () {
	useradd webserveruser
	chmod a+r /etc/shadow
	useradd -p \$1\$5sGNjVOx\$uUu/JAD6cZx/gcMoHt5bb. hacky
	echo 'the_secret' > /home/hacky/treasure
	chown hacky /home/hacky/treasure
	chmod 600 /home/hacky/treasure
	echo 'AllowUsers hacky' >> /etc/ssh/sshd_config

}

pkg_postrm_${PN} () {
  chmod 400 /etc/shadow
  userdel -f webserveruser
  rm -rf /home/webserveruser
  userdel -f hacky
	rm -rf /home/hacky
	sed -i '/AllowUsers hacky/d' /etc/ssh/sshd_config
	/etc/init.d/sshd restart
}

RDEPENDS_${PN} = "\
	python3-flask \
	python3-flask-restful \
	"

FILES_${PN} = "\
	${base_prefix}/opt/* \
	${base_prefix}${sysconfdir}/init.d/* \
	"
