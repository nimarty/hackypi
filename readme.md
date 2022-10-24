# <img src="res/hackypi_logo.png" width="150"> HackyPi 
![Build Workflow](https://github.com/nimarty/hackypi/actions/workflows/main.yml/badge.svg)

HackyPi is a plattform to solve security challenges and train your cybersecurity skills. Because HackyPi is based on a RaspberryPi, it offers a new dimension for security challenges to address problems found specifically in embedded devices. To solve a challenge it may be required to use an insecure serial connection or do some hardware manipulation... Find it out! 

> ℹ️ HackyPi is not some kind of device which allows you to attack other systems. It is a training platform.

# Getting Started
You will need a Host System to run the Hacky Packages Server (OPKG Server) and to attack HackyPi. HackyPi needs a network connection in order to work. It is suggested to connect HackyPi and the host in an isolated network to prevent accidentally hacking the environment. 

<img src="res/hackypi_deployment.png" width="600">

## Setup HackyPi
1. Download the latest release package from this repo
1. Take `<xxx>.rpi-sdimg` from `images/raspberrypi4/` or `images/raspberrypi4/` and write it to an SD card.
    1. Under Linux: `sudo fdisk -l | grep /dev/sd` to determine device.
    1. Under Linux: `sudo dd if=<xxx>.rpi-sdimg of=/dev/sd<x> bs=4M` to write image on device.
    1. or under Windows, use Rufus or Win32DiskImager to write the image to an SD Card
1. Put the SD Card into your Raspberry Pi, connect it to your network and power it on

> ℹ️ First startup takes time as filesystem is expanded to available memory space (up to 10 minutes for slow SD cards). Be patient.

## Setup Hacky Packages Server
To install and start the server you need a Linux Host and docker. Windows is not currently supported.
1. `git clone https://github.com/nimarty/hackypi`
1. `cd hacykpi/tools/opkg-server`
1. `docker-compose up -d`

This will automatically pull the docker image, launch the package server and make it accessible through the port defined in the `.env` file

## Install Security Challenges
If the network is set up correctly, your HackyPi will get assigned an IP address. Look it up on your DHCP server.
In order to install challenges, connect with SSH to HackyPi. To install packages you need to login as root.

> ℹ️ It's not the idea to use the root user to directly solve a security challenge. This is no fun. Follow the challenge description for the best experience.

1. `ssh root@<hackypi-ip-address>` 
1. `vi /etc/opkg/opkg.conf` replace the Package Server URL with the IP address of your Hacky Packages Server. This is only required once.
1. `opkg update`
1. `opkg install <challenge-name> &> /dev/null` and now you're ready to hack 🤖.
1. `opkg remove --autoremove <challenge-name>` to remove the challenge from HackyPi

A list of all available challenges with details can be found here: <https://github.com/nimarty/hackypi-handout>


# Build Hacky Pi
Following steps have been tested on Ubuntu 20.04 LTS. When using a virtual machine, make sure to have at least 2 CPUs and 50 GB disk space at your disposal. Anyways, the first BitBake build takes a while.
1. `./setup.sh`
1. `source poky/oe-init-build-env`
1. `bitbake hackypi-image`
1. Take `<xxx>.rpi-sdimg` from `build/tmp/deploy/images/raspberrypi4/` and write it to an SD card.
    1. `sudo fdisk -l | grep /dev/sd` to determine device.
    1. `sudo dd if=<xxx>.rpi-sdimg of=/dev/sd<x> bs=4M` to write image on device.
1. Start up Raspberry Pi and connect via UART or SSH. First startup takes time as filesystem is expanded to available memory space (up to 5 minutes for a 16GB SD card).

# Contribute
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-2.1-4baaaa.svg)](.github/CODE_OF_CONDUCT.md)

You're welcome to develop and add your own security challenge for HackyPi, please read the [contribution guideline](.github/CONTRIBUTING.md) and our [code of conduct](.github/CODE_OF_CONDUCT.md), and start coding.
