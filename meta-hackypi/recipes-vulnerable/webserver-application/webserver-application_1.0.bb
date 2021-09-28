# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

inherit pkgconfig

# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "\
	file://webserver.py \
	file://webserver.service \
	"

# NOTE: no Makefile found, unable to determine what needs to be done

do_configure () {
	# Specify any needed configure commands here
	:
}

do_compile () {
	# Specify compilation commands here
	:
}

do_install () {
	# Specify install commands here
	install -d ${D}${base_prefix}/opt/webserver/
	install -m 0755 ${WORKDIR}/webserver.py ${D}${base_prefix}/opt/webserver/
}


RDEPENDS_${PN} = "\
	python3-smbus \
	python3-flask \
	python3-flask-restful \
	"

FILES_${PN} = "${base_prefix}/opt/*"

