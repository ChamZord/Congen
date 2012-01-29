#!/bin/bash

function usage
{
    echo "usage: ./main.sh <theorem> <start> <end> <isabelle>"
    echo "    <theorem> = name of the theorem (e.g. MyTheorem) (NO SPACES!)"
    echo "    <start> = integer to start counting lemmas (recommended 0)"
    echo "    <end> = integer to stop lemma attempts (recommended 10)"
    echo "    <isabelle> = path to the Isabelle program (e.g. /usr/local/Isabelle)"
}

if [ $1 = "--help" ]; then
    usage;
    exit;
fi

if [ $1 = "-h" ]; then
    usage;
    exit;
fi

if [ $1 = "-?" ]; then
    usage;
    exit;
fi

if [ $1 = "?" ]; then
    usage;
    exit;
fi

eval "echo -e \"\nGenerating a theorem (conjecture attempt $2 of $3)...\n\";";

if [ $2 = $3 ]; then
    sed -i 's/New lemma/Try again/' check.txt;
    new=0;
else
    new=$(( $2 + 1 ));
fi

if ! grep -q "New lemma" ./check.txt; then
    eval "java TheRandomizer";
fi

eval "$4/bin/isabelle-process -e 'use_thy \"./TheGenerator\";' -q > ./output.txt";
sleep 5;

eval "java TheRandomizer \"Check\"";

if grep -q "Try again" ./check.txt; then
    eval "./main.sh $1 0 $3 $4";
elif grep -q "New lemma" ./check.txt; then
    eval "./main.sh $1 $new $3 $4";
else
    eval "cp TheGenerator.thy ./proven_theorems/TheGenerator.thy";
    eval "mv ./proven_theorems/TheGenerator.thy ./proven_theorems/$1.thy";
    eval "java WriteUp \"$1\"";
    sed -i 's/It works!/Try again/' check.txt;
    echo "Done!";
fi
