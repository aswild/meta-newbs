#!/bin/bash
# var-log-mount.sh
# Copyright 2019 Allen Wild
# SPDX-License-Identifier: MIT
#
# Script to use /home/log as /var/log if possible, falling back
# to /var/volatile/log

msg() {
    echo "$*" >&2
}

die() {
    msg "Error: $*"
    exit 1
}

logdest=/var/log
if [[ -L /var/log ]]; then
    logdest="$(readlink -f /var/log)"
    msg "Warning: /var/log is a symlink to $logdest"
fi

[[ -d $logdest ]] || die "$logdest is not a directory"

do_mount() {
    if [[ -w /home/log ]]; then
        msg "Using /home/log for /var/log"
        mount --bind /home/log $logdest || die "mount failed"
    else
        mkdir -p /var/volatile/log \
            || die "failed to create /var/volatile/log"
        mount --bind /var/volatile/log $logdest \
            || die "mount failed"
    fi

    if getent group systemd-journal &>/dev/null; then
        if [[ ! -d /var/log/journal ]]; then
            msg "Creating /var/log/journal"
            install -d -o root -g systemd-journal -m 2755 /var/log/journal \
                || die "failed to create /var/log/journal"
        elif [[ "$(stat -c %G /var/log/journal)" != "systemd-journal" ]]; then
            msg "Fixing ownership of /var/log/journal"
            chgrp -R systemd-journal /var/log/journal \
                || msg "warning: failed to change group ownership of /var/log/journal"
        fi
    fi
}

do_unmount() {
    if cut -d' ' -f2 /proc/mounts | grep -q "^${logdest}\$"; then
        msg "Unmounting $logdest"
        # we need the lazy unmount because this will be running while systemd-journald
        # still has its journal files open (hard to sequence around that).
        # Unmounting lazily removes the mountpoint from the init namespace, but the kernel
        # won't really finish unmounting until after journald's done
        umount --lazy $logdest
    fi
}

action="${1:-mount}"
case $action in
    mount) do_mount ;;
    unmount) do_unmount ;;
    *) msg "Usage: $0 {mount | unmount}"; exit 1 ;;
esac
