rem creates a symbolic link which you can move to your own java project
rem run as administrator

cd /d "%~dp0"

mklink /d "%cd%\gfc" "%cd%\src\gfc"

pause
