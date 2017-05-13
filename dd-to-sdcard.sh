#!/bin/bash

disk=/dev/sdd
image=core-image-newbs

while [[ $# != 0 ]]; do
    case $1 in
        sd*)
            disk=/dev/$1
            ;;
        /*)
            disk=$1
            ;;
        mce)
            image=newbs-mce-image
            ;;
        *)
            echo "Unknown option: $1"
            exit 1
            ;;
    esac
    shift
done

set -xe
[[ -e ${disk}1 ]]
[[ -e ${disk}2 ]]

sudo dd if=${image}-raspberrypi3.boot.vfat of=${disk}1 bs=1M
sudo dd if=${image}-raspberrypi3.squashfs-xz of=${disk}2 bs=1M
sync
sync
