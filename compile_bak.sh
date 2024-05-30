#!/bin/bash

cd ./out/production/JDK1.8_Source
jar cf0 rt_debug.jar *
echo "5lovefei" | sudo mv rt_debug.jar /Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre/lib/endorsed