#! /usr/bin/bash

LINE=1
Errors=0


while read -r CURRENT_LINE
	do
		((Errors++))
	((LINE++))
done < <(grep "Error" server.log)

echo "$Errors"

