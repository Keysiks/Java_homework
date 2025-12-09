#!/bin/bash

ROOT="/Users/kiriill/Documents/ProgMe/src/Test9-11/HW9-11"
OUT="$ROOT/out"

rm -rf "$OUT"
mkdir -p "$OUT"

javac -d "$OUT" \
  "$ROOT"/base/*.java \
  "$ROOT"/expression/*.java \
  "$ROOT"/expression/exceptions/*.java \
  "$ROOT"/expression/common/*.java \
  "$ROOT"/expression/parser/*.java \


java -ea -cp "$OUT" expression.exceptions.ExceptionsTest easy Base
