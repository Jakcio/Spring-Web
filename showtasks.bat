call %CATALINA_HOME%\bin\startup.bat
if "%ERRORLEVEL%" == "0" goto web
echo.
echo Cannot run startup.bat

:web
call start chrome http://localhost:8080/crud/v1/task/getTasks
echo Work is finished