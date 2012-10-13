#!/bin/bash
echo "Building..."; 
ant debug;
echo ""; 
echo "";
echo "Installing....";
adb install -r bin/Spuzzle-debug.apk;
