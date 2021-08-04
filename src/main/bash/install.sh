#!/bin/bash

{
  echo "Install? [Y|N]"
  read -r install
  if [ "$install" == "Y" ] || [ "$install" == "y" ]; then
    cd /usr/share || (
      echo "Couldn't find directory \"/usr/share\"!"
      exit
    )
    sudo rm -r PiCoder || (
      echo "Something went wrong. Please try again."
      exit
    )
    sudo git clone https://github.com/JakobWilms/PiCoder || (
      echo "Couldn't download sources. Is git installed?"
      exit
    )
    cd PiCoder || (
      echo "Downloaded sources not found. Please try again."
      exit
    )
    sudo chmod +x src/main/bash/*
    sudo cp src/main/bash/picoder.sh /usr/bin/picoder
  else
    echo "Installation cancelled."
    exit
  fi
} || {
  echo "Something went wrong. Please check the following and try again!"
  echo "OS = Linux"
  echo "Git is installed"
  echo "You are root"
}
