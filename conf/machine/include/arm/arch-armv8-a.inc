require conf/machine/include/arm/arch-arm64.inc

TUNEVALID[armv8] = "Enable instructions for ARMv8"
TUNECONFLICTS[armv8] = "armv4 armv5 armv6 armv7 armv7a armv7ve"

TUNE_CCARGS .= " \
${@' -march=armv8-a+crypto+crc' if bb.utils.contains('TUNE_FEATURES', [ 'armv8', 'cryptov8', 'crcv8' ], True, False, d) else \
' -march=armv8-a+crypto' if bb.utils.contains('TUNE_FEATURES', [ 'armv8', 'cryptov8' ], True, False, d) else \
' -march=armv8-a+crc' if bb.utils.contains('TUNE_FEATURES', [ 'armv8', 'crcv8' ], True, False, d) else \
bb.utils.contains('TUNE_FEATURES', 'armv8', ' -march=armv8-a', '', d)}"

TUNE_CCARGS_MFPU .= "\
${@' crypto-neon-fp-armv8' if bb.utils.contains('TUNE_FEATURES', [ 'armv8', 'neon', 'cryptov8' ], True, False, d) else \
' neon-fp-armv8' if bb.utils.contains('TUNE_FEATURES', [ 'armv8', 'neon' ], True, False, d) else \
bb.utils.contains('TUNE_FEATURES', [ 'armv8' ], ' fp-armv8', '', d)}"

MACHINEOVERRIDES =. "${@bb.utils.contains('TUNE_FEATURES', 'armv8', 'armv8:', '' ,d)}"

AVAILTUNES += "armv8 armv8-crypto armv8-crc armv8-crypto-crc"
TUNE_FEATURES_tune-armv8 = "armv8"
TUNE_FEATURES_tune-armv8-crypto = "${TUNE_FEATURES_tune-armv8} cryptov8"
TUNE_FEATURES_tune-armv8-crc = "${TUNE_FEATURES_tune-armv8} crcv8"
TUNE_FEATURES_tune-armv8-crypto-crc = "${TUNE_FEATURES_tune-armv8} cryptov8 crcv8"
PACKAGE_EXTRA_ARCHS_tune-armv8 = "armv8"
PACKAGE_EXTRA_ARCHS_tune-armv8-crypto = "${PACKAGE_EXTRA_ARCHS_tune-armv8} armv8-crypto"
PACKAGE_EXTRA_ARCHS_tune-armv8-crc = "${PACKAGE_EXTRA_ARCHS_tune-armv8} armv8-crc"
PACKAGE_EXTRA_ARCHS_tune-armv8-crypto-crc = "${PACKAGE_EXTRA_ARCHS_tune-armv8} armv8-crypto-crc"

AVAILTUNES += "armv8-neon armv8-neon-crypto armv8-neon-crc armv8-neon-crypto-crc"
TUNE_FEATURES_tune-armv8-neon = "${TUNE_FEATURES_tune-armv8} neon"
TUNE_FEATURES_tune-armv8-neon-crypto = "${TUNE_FEATURES_tune-armv8-crypto} neon"
TUNE_FEATURES_tune-armv8-neon-crc = "${TUNE_FEATURES_tune-armv8-crc} neon"
TUNE_FEATURES_tune-armv8-neon-crypto-crc = "${TUNE_FEATURES_tune-armv8-crypto-crc} neon"
PACKAGE_EXTRA_ARCHS_tune-armv8-neon = "${PACKAGE_EXTRA_ARCHS_tune-armv8} armv8-neon"
PACKAGE_EXTRA_ARCHS_tune-armv8-neon-crypto = "${PACKAGE_EXTRA_ARCHS_tune-armv8-crypto} armv8-neon-crypto"
PACKAGE_EXTRA_ARCHS_tune-armv8-neon-crc = "${PACKAGE_EXTRA_ARCHS_tune-armv8-crc} armv8-neon-crc"
PACKAGE_EXTRA_ARCHS_tune-armv8-neon-crypto-crc = "${PACKAGE_EXTRA_ARCHS_tune-armv8-crypto-crc} armv8-neon-crypto-crc"

AVAILTUNES += "armv8hf armv8hf-crypto armv8hf-crc armv8hf-crypto-crc"
TUNE_FEATURES_tune-armv8hf = "${TUNE_FEATURES_tune-armv8} callconvention-hard"
TUNE_FEATURES_tune-armv8hf-crypto = "${TUNE_FEATURES_tune-armv8-crypto} callconvention-hard"
TUNE_FEATURES_tune-armv8hf-crc = "${TUNE_FEATURES_tune-armv8-crc} callconvention-hard"
TUNE_FEATURES_tune-armv8hf-crypto-crc = "${TUNE_FEATURES_tune-armv8-crypto-crc} callconvention-hard"
PACKAGE_EXTRA_ARCHS_tune-armv8hf = "armv8hf"
PACKAGE_EXTRA_ARCHS_tune-armv8hf-crypto = "${PACKAGE_EXTRA_ARCHS_tune-armv8hf} armv8hf-crypto"
PACKAGE_EXTRA_ARCHS_tune-armv8hf-crc = "${PACKAGE_EXTRA_ARCHS_tune-armv8hf} armv8hf-crc"
PACKAGE_EXTRA_ARCHS_tune-armv8hf-crypto-crc = "${PACKAGE_EXTRA_ARCHS_tune-armv8hf} armv8hf-crypto-crc"

AVAILTUNES += "armv8hf-neon armv8hf-neon-crypto armv8hf-neon-crc armv8hf-neon-crypto-crc"
TUNE_FEATURES_tune-armv8hf-neon = "${TUNE_FEATURES_tune-armv8hf} neon"
TUNE_FEATURES_tune-armv8hf-neon-crypto = "${TUNE_FEATURES_tune-armv8hf-crypto} neon"
TUNE_FEATURES_tune-armv8hf-neon-crc = "${TUNE_FEATURES_tune-armv8hf-crc} neon"
TUNE_FEATURES_tune-armv8hf-neon-crypto-crc = "${TUNE_FEATURES_tune-armv8hf-crypto-crc} neon"
PACKAGE_EXTRA_ARCHS_tune-armv8hf-neon = "${PACKAGE_EXTRA_ARCHS_tune-armv8hf} armv8hf-neon"
PACKAGE_EXTRA_ARCHS_tune-armv8hf-neon-crypto = "${PACKAGE_EXTRA_ARCHS_tune-armv8hf-crypto} armv8hf-neon-crypto"
PACKAGE_EXTRA_ARCHS_tune-armv8hf-neon-crc = "${PACKAGE_EXTRA_ARCHS_tune-armv8hf-crc} armv8hf-neon-crc"
PACKAGE_EXTRA_ARCHS_tune-armv8hf-neon-crypto-crc = "${PACKAGE_EXTRA_ARCHS_tune-armv8hf-crypto-crc} armv8hf-neon-crypto-crc"

TUNEVALID[cryptov8] = "Enable armv8 crypto extensions"
TUNEVALID[crcv8] = "Enable armv8 crc extensions"

