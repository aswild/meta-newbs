# Gold linker breaks the armstubs, force using BFD
export LD8 = "${@d.getVar('LD').replace('-ld ', '-ld.bfd ')}"
