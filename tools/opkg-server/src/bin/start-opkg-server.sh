#!/bin/sh

# 1. Build package list
build_package_list()
{
	cd /packages
	rm -rf Packages.gz
	echo "Building package list"
	find . -name Packages.gz -exec rm {} +
	find . -name Packages.stamps -exec rm {} +

	/usr/bin/opkg-make-index . > Packages
	gzip ./Packages

	chmod -R 755 /packages
}

echo "Starting nginx"
nginx -g "daemon off;" &

while [ : ]
do
	build_package_list
	sleep 60
done
