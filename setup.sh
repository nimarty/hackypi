#!/bin/bash

# general config
PROJECT_PATH="$PWD"

# install dependencies
sudo apt install -y bc build-essential chrpath cpio diffstat gawk git texinfo wget python3-distutils chrpath diffstat

# checkout meta-layers
git clone --depth 1 git://git.yoctoproject.org/poky.git --branch dunfell --single-branch
git clone --depth 1 git://git.yoctoproject.org/meta-raspberrypi.git --branch dunfell --single-branch
git clone --depth 1 git://git.openembedded.org/meta-openembedded --branch dunfell --single-branch

# init build
source poky/oe-init-build-env

# overwrite auto generated config with own config
pushd $PROJECT_PATH
cp -rf conf/local.conf build/conf/local.conf
cp -rf conf/bblayers.conf build/conf/bblayers.conf
popd
