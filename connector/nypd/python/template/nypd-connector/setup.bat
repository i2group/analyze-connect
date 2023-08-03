@echo off
@REM i2, i2 Group, the i2 Group logo, and i2group.com are trademarks of N.Harris Computer Corporation.
@REM © N.Harris Computer Corporation (2023)

echo Checking if Python is installed...
python -V
if %errorlevel% neq 0 exit /b %errorlevel%

for /f "tokens=2" %%a in ('python -V') do set version=%%a

set VERSION=%version%
echo Detected Python v%VERSION%.

set NUMBER=%VERSION%/Python

echo Installing pipenv...
pip install pipenv

echo Checking pipenv version...
pipenv --version
if %errorlevel% neq 0 (
    echo:
    echo Update the PATH variable in your environment to include the locations of both the Python application and its scripts directory.
    exit /b %errorlevel%
)

echo Running Python...
pipenv --python %NUMBER%

echo Creating Piplock.file...
pipenv lock

echo Syncing dependencies...
pipenv sync
