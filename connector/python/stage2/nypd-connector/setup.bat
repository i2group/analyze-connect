@ECHO off

ECHO (1/3) Installing pipenv...
pip install pipenv

ECHO (2/3) Creating the virtual environment...
FOR /f "delims=" %%A in ('python -V') DO SET "var=%%A"
SET version=%var:~7%
pipenv --python %version%

ECHO (3/3) Installing dependencies...
pipenv sync