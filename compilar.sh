#!/bin/bash
mkdir -p out
javac -d out src/Main.java
if [ $? -eq 0 ]; then
  echo "Compilado OK. Ejecutando..."
  java -cp out bst.Main
else
  echo "Error de compilacion."
fi
