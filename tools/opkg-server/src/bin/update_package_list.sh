#!/bin/sh

if [ -z "$1" ]; then
	PACKAGE_DIR=/packages
else
	PACKAGE_DIR=$1
fi

if [ -d "${PACKAGE_DIR}" ]; then
	echo "Cleanup packages dir \"${PACKAGE_DIR}\""
	cd "${PACKAGE_DIR}"
	rm -f Packages.new.stamps
	rm -f Packages.new.gz
	rm -f Packages.new

	echo "Update package list"
	/usr/bin/opkg-utils/opkg-make-index -p Packages.new .
	mv Packages.new Packages
	mv Packages.new.gz Packages.gz
	mv Packages.new.stamps Packages.stamps

	echo "Update packages access permissions"
	chmod -R 755 "${PACKAGE_DIR}"
else
	echo "invalid package dir \"${PACKAGE_DIR}\""
	exit 1
fi

