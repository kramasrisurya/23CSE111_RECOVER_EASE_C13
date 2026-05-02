#!/bin/bash
mkdir -p out data images
javac -sourcepath src -d out src/Main.java && java -cp out Main
