@echo off
@REM i2, i2 Group, the i2 Group logo, and i2group.com are trademarks of N.Harris Computer Corporation.
@REM © N.Harris Computer Corporation (2022)
setlocal

IF NOT DEFINED SHELL_PATH (
    SET SHELL_PATH=%PROGRAMFILES%\Git\bin\sh.exe
)

"%SHELL_PATH%" "%~dp0setup.sh" %*
