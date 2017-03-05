#!/bin/bash

[[ -n $1 ]] && disk=/dev/${1#/dev/}
[[ -z $disk ]] && disk=/dev/sdi

set -xe
[[ -e ${disk}1 ]]
[[ -e ${disk}2 ]]

sudo dd if=core-image-newbs-raspberrypi3.boot.vfat of=${disk}1 bs=1M
sudo dd if=core-image-newbs-raspberrypi3.squashfs-xz of=${disk}2 bs=1M
sync
sync
