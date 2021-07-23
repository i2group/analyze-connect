@echo off
setlocal

IF NOT DEFINED SHELL_PATH (
    SET SHELL_PATH=%PROGRAMFILES%\Git\bin\sh.exe
)

"%SHELL_PATH%" "%~dp0setup.sh" %*
