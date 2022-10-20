SUMMARY = "Command line tool to manage HEMS from the terminal"
LICENSE = "CLOSED"
LIC_FILE_CHMSUM = ""

SRC_URI = "\
	file://CMakeLists.txt \
	file://main.cpp \
	"

S = "${WORKDIR}"

inherit cmake

EXTRA_OECMAKE = ""

RDEPENDS_${PN} = " \
    gdb \
    "
