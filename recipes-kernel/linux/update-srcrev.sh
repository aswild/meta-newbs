#!/bin/bash
set -e

branch=rpi-4.14.y
repo_url='https://github.com/raspberrypi/linux'

rev=$(git ls-remote $repo_url refs/heads/$branch | cut -f1)
if [[ -z $rev ]]; then
    echo "Error: revision for branch $branch not found"
    exit 1
fi
makefile=$(curl -sSL "https://raw.githubusercontent.com/raspberrypi/linux/${rev}/Makefile")

ver=$(awk '/^VERSION/{print $NF}' <<<"$makefile")
patch=$(awk '/^PATCHLEVEL/{print $NF}' <<<"$makefile")
sub=$(awk '/^SUBLEVEL/{print $NF}' <<<"$makefile")
linux_version="${ver}.${patch}.${sub}"

sed -i -e "s/^LINUX_VERSION.*/LINUX_VERSION = \"${linux_version}\"/" \
       -e "s/^SRCREV.*/SRCREV = \"${rev}\"/" \
       linux-raspberrypi-newbs_4.14.bb
