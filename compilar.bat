@echo off
REM ============================================================
REM  Compilar y ejecutar el BST — Universidad Da Vinci
REM  Uso: ejecutar este archivo desde la carpeta raíz del proyecto
REM ============================================================

echo.
echo  ╔══════════════════════════════════════════╗
echo  ║  Compilando Árbol Binario de Búsqueda    ║
echo  ╚══════════════════════════════════════════╝
echo.

REM Crear carpeta de clases compiladas
if not exist "out\main" mkdir out\main
if not exist "out\test" mkdir out\test

REM Compilar clases principales
echo  [1/3] Compilando src...
javac -d out\main src\main\java\bst\Nodo.java src\main\java\bst\BST.java src\main\java\bst\Main.java

if %errorlevel% neq 0 (
    echo  ERROR: La compilacion fallo.
    pause
    exit /b 1
)

REM Compilar tests
echo  [2/3] Compilando tests...
javac -cp out\main -d out\test src\test\java\bst\BSTTest.java

echo  [3/3] Ejecutando demo...
echo.
java -cp out\main bst.Main

echo.
echo  ══════════════════════════════════════════
echo  Ejecutando pruebas unitarias...
echo  ══════════════════════════════════════════
java -cp "out\main;out\test" bst.BSTTest

pause
