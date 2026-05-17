#!/bin/bash
# ============================================================
#  Compilar y ejecutar el BST — Universidad Da Vinci
#  Uso: bash compilar.sh
# ============================================================

echo ""
echo "  ╔══════════════════════════════════════════╗"
echo "  ║  Compilando Árbol Binario de Búsqueda    ║"
echo "  ╚══════════════════════════════════════════╝"
echo ""

mkdir -p out/main out/test

echo "  [1/3] Compilando src..."
javac -d out/main \
  src/main/java/bst/Nodo.java \
  src/main/java/bst/BST.java \
  src/main/java/bst/Main.java

if [ $? -ne 0 ]; then
  echo "  ERROR: La compilación falló."
  exit 1
fi

echo "  [2/3] Compilando tests..."
javac -cp out/main -d out/test src/test/java/bst/BSTTest.java

echo "  [3/3] Ejecutando demo..."
echo ""
java -cp out/main bst.Main

echo ""
echo "  ══════════════════════════════════════════"
echo "  Ejecutando pruebas unitarias..."
echo "  ══════════════════════════════════════════"
java -cp "out/main:out/test" bst.BSTTest
