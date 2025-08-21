#!/bin/bash
SNODE=CDNODE02

for i in $(seq 1 9999); do
    inc=$(printf "%04d" "$i")

    # Executa o script com a nova sequencia
    /opt/send_file.sh /opt/sample_file_10K /tmp/sample_file_$inc $SNODE

done
