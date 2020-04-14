#!/bin/bash

cd /home/onyr/Documents/2A/computer_sciences/amgs/src/amgs
javac *.java

# WARN: Can't run java Main with Main being defined inside amgs package !
cd ..
java amgs.Main
