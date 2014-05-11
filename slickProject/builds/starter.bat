@echo off
cls

echo.
echo          HARGEN PROJECT LOADER         
echo.

for /f "tokens=*" %%a in ('dir hargen /b /od') do set newest=%%a

SET INPUT=hargen/%newest%
echo =======================================
echo ==  Load "%newest%" ?          ==
echo ==  Press ENTER to continue or type  ==
echo ==  a new file name (w/o extension)  ==
echo =======================================
echo.
SET /P INPUT= : 

:RUN
echo loading: %INPUT%
echo.
java -Djava.library.path="hargen\lib\natives" -Xmx1024M -Xms1024M -jar %INPUT%

:END

echo.
pause