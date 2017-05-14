# RPi userland recipe doesn't PROVIDE libgles1, but we can pretend it does

DEPENDS_remove = "virtual/libgles1"
