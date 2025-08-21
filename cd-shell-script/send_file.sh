#!/bin/bash
# Sample script that invokes the Connect:Direct for UNIX CLI 
# and submits a process inline to copy a file to a remote node.
#
# $1 source file.
# $2 destination file.
# $3 remote cdnode.

set -v

ndmcli -x << EOJ
submit SENDF process snode=$3 
COPY0 copy from (file=$1)
            to  (file=$2)
pend ;
EOJ
