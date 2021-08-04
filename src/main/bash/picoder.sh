#!/bin/bash

cd /usr/share || (
  echo "Couldn't find directory \"/usr/share\"!"
  exit
)

if [ "$1" == "install" ]; then
  sudo ./PiCoder/src/main/bash/install.sh
elif [ "$1" == "encode" ]; then
  java -jar ./PiCoder/PiCoder.jar "$1" "$2" "$3" "$4" "$5"
elif [ "$1" == "decode" ]; then
  java -jar ./PiCoder/PiCoder.jar "$1" "$2" "$3" "$4" "$5"
elif [ "$1" == "generate-key" ]; then
  java -jar ./PiCoder/PiCoder.jar "$1" "$2"
fi
