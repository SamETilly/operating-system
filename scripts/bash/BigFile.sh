#! /usr/bin/bash

read -p "Enter a filename " file
if [[ -f $file ]]; then
	minimumsize=1048576
	actualsize=$(wc -c <"$file")
	if [ $actualsize -gt $minimumsize ]; then
    		echo Warning: File is too large
	else
    		echo File size is within limits
	fi
else
	echo File does not exist
fi
