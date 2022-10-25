#!/bin/bash

# general config
PROJECT_PATH="$PWD"

# install dependencies
sudo apt install -y -qq bc build-essential chrpath cpio diffstat gawk git texinfo wget python3-distutils chrpath diffstat repo || exit 1

# checkout meta-layers
#git clone --depth 10 git://git.yoctoproject.org/poky.git --branch dunfell --single-branch
#pushd poky
#git reset --hard 7f9b7f912e13451a4cd03b10a8090a5def68dc39 || exit 1
#popd
#
#git clone --depth 1 git://git.yoctoproject.org/meta-raspberrypi.git --branch dunfell --single-branch
#pushd meta-raspberrypi
#git reset --hard 2081e1bb9a44025db7297bfd5d024977d42191ed || exit 1
#popd
#
#git clone --depth 1 git://git.openembedded.org/meta-openembedded --branch dunfell --single-branch
#pushd meta-openembedded
#git reset --hard 6792ebdd966aa0fb662989529193a0940fbfee00 || exit 1
#popd

repo init -q --depth 10 -m manifest.xml -b enhancement/define-yocto-releae https://github.com/nimarty/hackypi || exit 1
cp -rf manifest.xml .repo/ || exit 1 # overwrite with local manifest
repo sync -q || exit 1

# init build
source poky/oe-init-build-env &> /dev/null || exit 1

# overwrite auto generated config with own config
pushd $PROJECT_PATH &> /dev/null
cp -rf conf/local.conf build/conf/local.conf || exit 1
cp -rf conf/bblayers.conf build/conf/bblayers.conf || exit 1
popd &> /dev/null


# build minimal image
# bitbake core-image-minimal
