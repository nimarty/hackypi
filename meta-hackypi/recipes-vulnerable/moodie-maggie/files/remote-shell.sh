#!/bin/sh

### BEGIN INIT INFO
# Provides:          remote-shell
# Required-Start:
# Required-Stop:
# Default-Start:     5
# Default-Stop:      0 6
# Short-Description: Create a bind shell restricted to localhost
### END INIT INFO

start() {
    nohup sh -c "/usr/bin/moody-maggie" &
}

stop() {
    killall moody-maggie
}

case "$1" in
    start)
        start
        ;;
    stop)
        stop
        ;;
    restart)
        stop
        start
        ;;
    *)
        echo "Usage: $0 {start|stop|restart}" >&2
        exit 1
        ;;
esac

exit 0
