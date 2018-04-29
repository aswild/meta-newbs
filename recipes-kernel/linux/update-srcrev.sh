#!/bin/bash
set -e

branch=rpi-4.14.y
repo_url='https://github.com/raspberrypi/linux'

rev=$(git ls-remote --heads $repo_url | awk "/refs\\/heads\\/${branch//./\\.}\$/{print \$1}")
makehead=$(curl -s "https://raw.githubusercontent.com/raspberrypi/linux/${rev}/Makefile" | egrep '^(VERSION|PATCHLEVEL|SUBLEVEL)')

ver=$(awk '/VERSION/{print $NF}' <<<"$makehead")
patch=$(awk '/PATCHLEVEL/{print $NF}' <<<"$makehead")
sub=$(awk '/SUBLEVEL/{print $NF}' <<<"$makehead")
linux_version="${ver}.${patch}.${sub}"

sed -i -e "s/^LINUX_VERSION.*/LINUX_VERSION = \"${linux_version}\"/" \
       -e "s/^SRCREV.*/SRCREV = \"${rev}\"/" \
       linux-raspberrypi-newbs_4.14.bb
