#! /usr/bin/env bash

CHAPTER_DIR_PREFIX=ch_
SOLUTION_PREFIX=solution_
SOLUTION_EXTENSION=.cpp
CXXFLAGS="-Wall -Wextra -Wpedantic"
EXECUTABLE="soln"

function prefix_num() {
  if [ $1 -lt 10 ]; then
    echo 0$1
  else
    echo $1
  fi
}

if [ $# -ne 2 ]; then
  echo "Usage: run_solution <chapter #> <excercise #>"
  exit -1
fi

chapter_dir=$CHAPTER_DIR_PREFIX$(prefix_num $1)
if [ ! -d $chapter_dir ]; then
  echo "Unknown chapter: $1 (dir: $chapter_dir)"
  exit -2
fi

solution_src=./$chapter_dir/$SOLUTION_PREFIX$(prefix_num $2)$SOLUTION_EXTENSION
if [ ! -f $solution_src ]; then
  echo "Solution in not available: $2 (src: $solution_src)"
  exit -3
fi

g++ $CXXFLAGS -o $EXECUTABLE $solution_src
if [ $? -ne 0 ]; then
  echo "Unable to compile $solution_src"
  exit -4
fi

./$EXECUTABLE
echo status: $?
rm $EXECUTABLE
