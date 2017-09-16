#! /usr/bin/env bash

SOLUTION_EXTENSION=.cpp
UV_FLAGS="-lm -O2 -std=c++11 -pipe -DONLINE_JUDGE"
CXXFLAGS="-Wall -Wextra -Wpedantic $(echo $UV_FLAGS)"

EXECUTABLE=$1

if [ $# -gt 3 ]; then
  echo "Usage: run_solution <problem #> [<input>]"
  exit -1
fi


solution_src=./src/$1$SOLUTION_EXTENSION

if [ ! -f $solution_src ]; then
  echo "Solution in not available (src: $solution_src)"
  exit -3
fi

g++ $CXXFLAGS -o $EXECUTABLE $solution_src
if [ $? -ne 0 ]; then
  echo "Unable to compile $solution_src"
  exit -4
fi

echo Running...

if [ $# -eq 2 ]; then
  ./$EXECUTABLE < $2
else
  ./$EXECUTABLE
fi
echo status: $?
rm $EXECUTABLE
