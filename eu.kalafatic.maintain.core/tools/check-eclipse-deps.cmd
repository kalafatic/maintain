@echo off
REM check-eclipse-deps.cmd - Try several Draw2D/Zest Maven coordinates to see which exist in your environment.
REM Usage: run from a Windows cmd.exe with Maven on PATH:
REM   cd /d C:\Users\petrk\git\repository\maintain\eu.kalafatic.maintain.core\tools
REM   check-eclipse-deps.cmd

setlocal enabledelayedexpansion
set RESULTS=%~dp0\results.txtn
if exist "%RESULTS%" del "%RESULTS%"

REM Ensure mvn is availablenwhere mvn >nul 2>&1
if errorlevel 1 (
  echo Maven (mvn) not found on PATH. Install Apache Maven and re-run this script.>&2
  exit /b 2
)

echo Checking candidate artifact coordinates. Output written to %RESULTS%
echo Timestamp: %date% %time%>"%RESULTS%"

rem List of candidate coordinates to test (format: groupId:artifactId:version)
set coords[0]=org.eclipse.draw2d:org.eclipse.draw2d:3.10.0
set coords[1]=org.eclipse.draw2d:org.eclipse.draw2d:3.9.0
set coords[2]=org.eclipse.draw2d:org.eclipse.draw2d:3.8.0
set coords[3]=org.eclipse.zest:org.eclipse.zest.core:1.5.0
set coords[4]=org.eclipse.zest:org.eclipse.zest.core:1.4.0
set coords[5]=org.eclipse.zest:org.eclipse.zest.core:2.0.0
set coords[6]=org.eclipse.zest:org.eclipse.zest.core.widgets:1.5.0
set coords[7]=org.eclipse.zest:org.eclipse.zest.core.widgets:1.4.0

for /L %%i in (0,1,7) do (
  call set coord=%%coords[%%i]%%
  echo Testing: !coord! | tee -a "%RESULTS%"
  mvn -q dependency:get -Dartifact=!coord! -Dtransitive=false >nul 2>&1
  if errorlevel 1 (
    echo FAILED: !coord! | tee -a "%RESULTS%"
  ) else (
    echo OK: !coord! | tee -a "%RESULTS%"
  )
  echo ------------------------------------- | tee -a "%RESULTS%"
)

echo Done. See %RESULTS% for full output.
endlocal
exit /b 0
