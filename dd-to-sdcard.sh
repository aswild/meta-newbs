#!/bin/bash
#
# !WARNING!
# Make sure you specify the correct disk! Overwriting the wrong drive would really suck.
# !WARNING!
#
# SD CARD FORMATTING:
# The bitbake output is separate filesystem images for /boot (vfat)
# and the rootfs (squashfs or ext4). This script uses dd to install them
# on a partitioned SD card.
#
# Use fdisk to partition the sdcard:
#   p1 = boot partition. Type "W95 FAT32" (b). Size 40-100MB
#   p2 = rootfs partition. Type "Linux" (83). Size: at least 250MB, but
#        up to a gig allows for installing ext4 versions of larger images
#   Neither of these partitions need to be formatted
#
# Optional but recommended: add a persistent /home partition. Format
# it as ext4 with the label RPI-HOME, and create a /root directory

disk=/dev/sdk
image=core-image-newbs
machine=raspberrypi3
rootfstype=squashfs-xz

while (($#)); do
    case $1 in
        sd*)
            disk=/dev/$1
            ;;
        mce)
            image=newbs-mce-image
            ;;
        mini)
            image=newbs-mini-image
            ;;
        dev)
            image=dev-image-newbs
            ;;
        ext)
            rootfstype=ext4
            ;;
        64)
            machine=raspberrypi3-64
            ;;
        a53)
            machine=raspberrypi3-a53
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

sudo dd if=${image}-${machine}.boot.vfat of=${disk}1 bs=1M
sudo dd if=${image}-${machine}.${rootfstype} of=${disk}2 bs=1M
sync
sync
