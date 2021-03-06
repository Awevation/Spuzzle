#!/bin/bash

if [ "$1" == "linux" ]; then
    echo "Building..."; 
    ant debug;
    echo ""; 
    echo "";
    echo "Installing....";
    adb install -r bin/Spuzzle-debug.apk;

    #start the app
    adb shell am start -a android.intent.action.MAIN -n com.awevation.spuzzle/.StartActivity;

elif [ "$1" == "droid" ]; then
    #Build script (for building on Android in terminalIDE, for most of the code ahead credit goes to SparticusRex

    #cd into the home dir - this way it works when run from inside vim or any other folder
    cd ~/projects/Spuzzle 

    #Clean up
    rm -rf build
    rm -rf dist

    #create the needed directories
    mkdir -m 770 -p dist
    mkdir -m 770 -p build/classes

    #Rmove the R.java file as will be created by aapt
    rm src/com/awevation/spuzzle/R.java 

    #Now use aapt
    echo Create the R.java file
    aapt p -f -v -M AndroidManifest.xml -F ./build/resources.res -I ~/android.jar -S res/ -J src/com/awevation/spuzzle 

    #cd into the src dir
    cd src

    #Now compile - note the use of a seperate lib (in non-dex format!)
    echo Compile the java code
    #javac -verbose -cp ../libs/demolib.jar -d ../build/classes com/awevation/spuzzle/StartActivity.java
    javac  -d ../build/classes com/awevation/spuzzle/StartActivity.java

    #Back out
    cd ..

    #Now into build dir
    cd build/classes/

    #Now convert to dex format (need --no-strict) (Notice demolib.jar at the end - non-dex format)
    echo Now convert to dex format
    dx --dex --verbose --no-strict --output=../spuzzle.dex com

    #Back out
    cd ../..

    #And finally - create the .apk
    apkbuilder ./dist/spuzzle.apk -v -u -z ./build/resources.res -f ./build/spuzzle.dex 

    #And now sign it
    cd dist
    signer spuzzle.apk spuzzle_signed.apk

    cd ..
    
    #Install the apk

    #cd into the home dir - this way it works when run from inside vim or any other folder
    cd ~/projects/Spuzzle

    #Remove the Old
    if [ -f /sdcard/demo_android_signed.apk ]; then
	rm /sdcard/spuzzle_signed.apk
    fi

    #Only works if APK is on the sdcard
    cp ./dist/spuzzle_signed.apk /sdcard/

    #Now try and view it..
    am start -a android.intent.action.VIEW -t application/vnd.android.package-archive -d file:///sdcard/spuzzle_signed.apk
    
    #and start it up!
    am start -a android.intent.action.MAIN -n com.awevation.spuzzle/.StartActivity;
fi
