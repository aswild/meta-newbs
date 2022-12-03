# RPi userland recipe doesn't PROVIDE libgles1, but we can pretend it does

DEPENDS:remove = "virtual/libgles1"
