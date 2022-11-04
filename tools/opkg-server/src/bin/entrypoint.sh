#!/bin/sh

if [ $1 = "develop" ]; then
	echo "update package list..."
	/usr/bin/update_package_list.sh
	echo "start crond..."
	/usr/sbin/crond -f -l 8 &
fi

echo "start nginx..."
# call entrypoint from nginx:alpine
exec /docker-entrypoint.sh "nginx" "-g" "daemon off;"

