#! /usr/bin/bash

for file in /home/sam-tilly/Documents/CS380/Test/*.txt
	do
		base=$(basename "$file")
		mv "$file" "/home/sam-tilly/Documents/CS380/Test/OLD_$base"
done

echo Done
