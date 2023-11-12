#!/bin/bash
set -e

if [[ $1 == -c || $1 == --commit ]]; then
    COMMIT=y
    shift
fi

kvers=${1:-6.1}
commitmsg='kernel: bump to '
bbfiles_commit=()

for kver in $kvers; do
    branch=rpi-${kver}.y
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

    bbfile=linux-raspberrypi-newbs_${kver}.bb
    oldversion=$(perl -ne 'print if s/^LINUX_VERSION\s*=\s*"(.*)"/\1/' $bbfile)
    if [[ $oldversion != $linux_version ]]; then
        commitmsg+="$linux_version, "
        bbfiles_commit+=($bbfile)
    fi

    sed -i -e "s/^LINUX_VERSION.*/LINUX_VERSION = \"${linux_version}\"/" \
           -e "s/^SRCREV.*/SRCREV = \"${rev}\"/" \
           $bbfile

    echo "set version to ${linux_version} rev ${rev}"
done

if [[ $COMMIT == y && -n $bbfiles_commit ]]; then
    commitmsg="${commitmsg%, }"
    git commit -m "$commitmsg"  "${bbfiles_commit[@]}"
fi
