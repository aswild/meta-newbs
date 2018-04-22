# db uses swpb inline asm instructions, which fail to compile on armv8
# clearing this variable removes --with-mutex=ARM/gcc-assembly
ARM_MUTEX_armv8 = ""
