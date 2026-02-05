$filename = Read-Host "Input the name of and path to a file"
$fileinfo = Get-Item $filename
$filesize = $fileinfo.Length
if ($filesize -gt 1048576){ "Warning: File is to large"}
else {"File size is within limits"}