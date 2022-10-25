#!/bin/bash

# general config
PROJECT_PATH="$PWD"

# install dependencies
sudo apt install -y -qq bc build-essential chrpath cpio diffstat gawk git texinfo wget python3-distutils chrpath diffstat repo || exit 1

# checkout meta-layers
repo init -q --depth 10 -m manifest.xml https://github.com/nimarty/hackypi || exit 1
cp -rf manifest.xml .repo/ || exit 1 # overwrite with local manifest
repo sync || exit 1

# init build
source poky/oe-init-build-env &> /dev/null || exit 1

# overwrite auto generated config with own config
pushd $PROJECT_PATH &> /dev/null
cp -rf conf/local.conf build/conf/local.conf || exit 1
cp -rf conf/bblayers.conf build/conf/bblayers.conf || exit 1
popd &> /dev/null


# build minimal image
# bitbake core-image-minimal
