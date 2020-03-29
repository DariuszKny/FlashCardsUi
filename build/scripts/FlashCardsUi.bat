@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  FlashCardsUi startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and FLASH_CARDS_UI_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\FlashCardsUi.jar;%APP_HOME%\lib\fontawesomefx-emojione-2.2.7-2.jar;%APP_HOME%\lib\fontawesomefx-fontawesome-4.7.0-5.jar;%APP_HOME%\lib\fontawesomefx-icons525-3.0.0-4.jar;%APP_HOME%\lib\fontawesomefx-materialdesignfont-1.7.22-4.jar;%APP_HOME%\lib\fontawesomefx-materialicons-2.2.0-5.jar;%APP_HOME%\lib\fontawesomefx-octicons-4.3.0-5.jar;%APP_HOME%\lib\fontawesomefx-weathericons-2.0.10-5.jar;%APP_HOME%\lib\fontawesomefx-controls-8.15.jar;%APP_HOME%\lib\jfoenix-8.0.8.jar;%APP_HOME%\lib\spring-boot-starter-web-2.2.5.RELEASE.jar;%APP_HOME%\lib\jpro-webapi-2019.2.2.jar;%APP_HOME%\lib\fontawesomefx-commons-8.15.jar;%APP_HOME%\lib\spring-boot-starter-json-2.2.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-validation-2.2.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-2.2.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-2.2.5.RELEASE.jar;%APP_HOME%\lib\spring-webmvc-5.2.4.RELEASE.jar;%APP_HOME%\lib\spring-web-5.2.4.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-2.2.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-2.2.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-logging-2.2.5.RELEASE.jar;%APP_HOME%\lib\jakarta.annotation-api-1.3.5.jar;%APP_HOME%\lib\spring-context-5.2.4.RELEASE.jar;%APP_HOME%\lib\spring-aop-5.2.4.RELEASE.jar;%APP_HOME%\lib\spring-beans-5.2.4.RELEASE.jar;%APP_HOME%\lib\spring-expression-5.2.4.RELEASE.jar;%APP_HOME%\lib\spring-core-5.2.4.RELEASE.jar;%APP_HOME%\lib\snakeyaml-1.25.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.10.2.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.10.2.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.10.2.jar;%APP_HOME%\lib\jackson-databind-2.10.2.jar;%APP_HOME%\lib\tomcat-embed-websocket-9.0.31.jar;%APP_HOME%\lib\tomcat-embed-core-9.0.31.jar;%APP_HOME%\lib\tomcat-embed-el-9.0.31.jar;%APP_HOME%\lib\jakarta.validation-api-2.0.2.jar;%APP_HOME%\lib\hibernate-validator-6.0.18.Final.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\log4j-to-slf4j-2.12.1.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.30.jar;%APP_HOME%\lib\spring-jcl-5.2.4.RELEASE.jar;%APP_HOME%\lib\jackson-annotations-2.10.2.jar;%APP_HOME%\lib\jackson-core-2.10.2.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\classmate-1.3.4.jar;%APP_HOME%\lib\logback-core-1.2.3.jar;%APP_HOME%\lib\slf4j-api-1.7.30.jar;%APP_HOME%\lib\log4j-api-2.12.1.jar;%APP_HOME%\lib\tomcat-annotations-api-9.0.31.jar

@rem Execute FlashCardsUi
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %FLASH_CARDS_UI_OPTS%  -classpath "%CLASSPATH%" com.jpro.flashCardsUi.HelloJProFXML %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable FLASH_CARDS_UI_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%FLASH_CARDS_UI_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
