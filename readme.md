# Hacky Pi :robot:
The goal of this project is to provide a minimal Raspberry Pi image with opkg support to install various vulnerable packages. These packages are also part of this repository. The idea is to have challenges linked to vulnerable packages.


![example workflow](https://github.com/nimarty/hackypi/actions/workflows/main.yml/badge.svg)

# Challenges
[List of challenges](challenges/readme.md)


# Build OS Image
1. `./setup.sh`
1. `source poky/oe-init-build-env`
1. `bitbake hackypi-image`
1. take `xxx.rpi-sdimg` from `build/tmp/deploy/images/raspberrypi4/` and write it to SD card
1. startup Raspberry Pi and connect via UART or SSH


# Build & install a package
1. `bitbake webserver-application` to build webserver package
1. setup an opkg package server (e.g. this one: [](https://github.com/nimarty/docker-private-opkg-repo)) which points to ipk build directory.
1. configure the opkg repository on the Raspberry Pi. Append the following line at the botom of `/etc/opkg/opkg.conf`
```
src/gz hackypackages http://<SERVER_URL>:<PORT>
```
1. run `opkg update` to update local package list
1. run `opkg install <PACKAGE_NAME>` to install a package
