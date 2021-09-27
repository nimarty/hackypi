# install dependencies
sudo apt install -y bc build-essential chrpath cpio diffstat gawk git python texinfo wget

# checkout meta-layers
git clone --depth 1 git://git.yoctoproject.org/poky.git --branch dunfell --single-branch
git clone --depth 1 git://git.yoctoproject.org/meta-raspberrypi.git --branch dunfell --single-branch
git clone --depth 1 git://git.openembedded.org/meta-openembedded --branch dunfell --single-branch

