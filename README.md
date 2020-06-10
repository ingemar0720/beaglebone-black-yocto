# beaglebone-black-yocto
build an image with yocto for beaglebone black
## setup

### prepare OS
1. Prepare a Ubuntu 64bits 18.04. I reserve 140GB space and 8GB ram to this VM
### install package
1. Install package in ubuntu
```
build-essential
chrpath
diffstat
gawk
libncurses5-dev
python3-distutils
texinfo
```
### clone poky repo
1. prepare your installation directory
2. clone poky repo. 
  ```
  git clone -b zeus git://git.yoctoproject.org/poky.git poky-zeus
  ```
3. clone other repo into poky repo directory 
  ```
  cd poky-zeus
  git clone -b zeus git://git.openembedded.org/meta-openembedded
  git clone -b zeus https://github.com/meta-qt5/meta-qt5
  git clone -b zeus git://git.yoctoproject.org/meta-raspberrypi # for rpi, skip if build for BBB
  git clone -b zeus git://git.yoctoproject.org/meta-security.git
  git clone -b zeus git://git.yoctoproject.org/meta-virtualization.git # for docker, skip if no need
  ```
### clone this repo
1. 
## build
1. setup bitbake and env
```
cd poky-zeus/ 
source oe-init-build-env ../bbb/build
```
2. setup build config
  - for BBB
  ```
  cp build/conf/local_bbb.conf /build/conf/local.conf
  cp build/bblayers_bbb.conf /build/conf/bblayers.conf
  ```
  - for rpi
  ```
  cp build/conf/local_rpi.conf /build/conf/local.conf
  cp build/bblayers_rpi.conf /build/conf/bblayers.conf
  ```
  update variable `machine` in local.conf. The lists of machine for raspberry is in below.
  ```
  raspberrypi (BCM2835)
  raspberrypi0 (BCM2835)
  raspberrypi0-wifi (BCM2835)
  raspberrypi2 (BCM2836 or BCM2837 v1.2+)
  raspberrypi3 (BCM2837)
  raspberrypi4 (BCM2838)
  raspberrypi-cm (BCM2835)
  raspberrypi-cm3 (BCM2837)
  ```
### command to build
```
bitbake bbb-image (for BBB)
bitbake rpi-image (for rpi)
```
## burn image
1. put in sd card and use fdisk to assure ubuntu detect sd card.
```
# /dev/$sdx is your SD card device 
fdisk /dev/$sdx 
```
2. go to image position 
```
# machine is the target machine for image
cd build/tmp/deploy/images/$machine/
# for raspberry
sudo dd bs=4M if=$machine.rpi-sdimg of=/dev/sdc

# for bbb
sudo dd bs=4M if=$machine.wic of=/dev/sdc
```
The whole build might take half day, then you could use serial console cable to validate the image.
