#!/bin/sh

# 1. Build package list
build_package_list()
{
	cd /packages
        rm -f Packages.new.stamps
        rm -f Packages.new.gz
        rm -f Packages.new

	echo "Build new package list"
	/usr/bin/opkg-make-index -p Packages.new .


	echo "Update package list"
        mv Packages.new Packages
	mv Packages.new.gz Packages.gz
	mv Packages.new.stamps Packages.stamps

	echo "Update packages access permissions"
	chmod -R 755 /packages
}

echo "Starting nginx"
nginx -g "daemon off;" &

while [ : ]
do
	build_package_list
	sleep 60
done
