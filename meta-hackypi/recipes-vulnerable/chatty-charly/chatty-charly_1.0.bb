# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

inherit pkgconfig update-rc.d

SRC_URI = "\
	file://webserver.py \
	file://webserver \
	"

INITSCRIPT_PARAMS = "start 02 2 3 4 5 . stop 01 0 1 6 ."
INITSCRIPT_NAME = "webserver"

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
	# Specify compilation commands here
	:
}

do_install () {
	# install application
	install -d ${D}${base_prefix}/opt/webserver/
	install -m 0755 ${WORKDIR}/webserver.py ${D}${base_prefix}/opt/webserver/

	# install init.d service
	install -d ${D}${base_prefix}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/webserver ${D}${base_prefix}${sysconfdir}/init.d/
}


RDEPENDS_${PN} = "\
	python3-flask \
	python3-flask-restful \
	"

FILES_${PN} = "\
	${base_prefix}/opt/* \
	${base_prefix}${sysconfdir}/init.d/* \
	"

