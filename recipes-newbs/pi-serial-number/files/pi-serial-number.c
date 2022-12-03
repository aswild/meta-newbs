/*
 * Copyright 2018 Allen Wild
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE
 *
 * pi-serial-number: a sample program to read a Pi's serial number by quering VCHI.
 * Link with -lvcos -lvchiq:arm -lvhcostif
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <interface/vmcs_host/vc_vchi_gencmd.h>

#define DIE(fmt, args...) do { \
        fprintf(stderr, "ERROR: " fmt "\n", ##args); \
        ret = 1; \
        goto out; \
    } while (0)

int main()
{
    VCHI_INSTANCE_T vchi_instance;
    VCHI_CONNECTION_T *vchi_connection = NULL;
    char buf[1024] = {0};
    int ret = 1;

    vcos_init();
    if (vchi_initialise(&vchi_instance) != 0)
        DIE("VCHI initialization failed\n");

    //create a vchi connection
    if (vchi_connect(NULL, 0, vchi_instance) != 0)
        DIE("VCHI connection failed\n");

    vc_vchi_gencmd_init(vchi_instance, &vchi_connection, 1);

    ret = vc_gencmd(buf, sizeof(buf), "otp_dump");
    if (ret != 0)
        DIE("vc_gencmd failed: %d", ret);

    // split lines of output
    bool found = false;
    char *line = strtok(buf, "\n");
    do
    {
        // serial number is at location 28
        if (!strncmp(line, "28:", 3))
        {
            printf("%s\n", line+3);
            found = true;
            break;
        }
    } while ((line = strtok(NULL, "\n")) != NULL);

    if (found)
        ret = 0;
    else
        DIE("vc_gencmd succeeded, but couldn't parse output");

out:
    vc_gencmd_stop();
    if ( vchi_disconnect( vchi_instance ) != 0)
    {
        fprintf(stderr, "VCHI disconnect failed\n");
        ret = 1;
    }

    return ret;
}
