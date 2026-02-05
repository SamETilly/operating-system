Get-ChildItem "C:\Users\still\Documents\CS380\Test\*.txt" | Rename-Item -NewName { "OLD_$($_.Name)"}
