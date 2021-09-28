# How to build
1. `./setup.sh`
1. `source poky/oe-init-build-env`
1. `bitbake hackypi-image`
1. take `xxx.rpi-sdimg` from `build/tmp/deploy/images/raspberrypi4/` and write it to SD card
1. startup raspberry pi and connect via UART or SSH
