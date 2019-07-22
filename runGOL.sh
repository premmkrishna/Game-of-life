#!/usr/bin/env bash

./gradlew clean build
path=`pwd`
buildDir=$path"/build/libs"
applicationJar="game-of-life-1.0-SNAPSHOT.jar"
totalPath=$buildDir/$applicationJar
java -jar $totalPath
