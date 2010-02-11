if NOT exist "jar-output" md jar-output
if NOT exist "jar-output" goto quit

cd jar-output
echo i|del *.*

copy ..\src\S.java .

java -cp ..\bin\ Optimizer
javac -target 1.5 -g:none S.java
jar cvfM 4Kraft-raw.jar S.class

del S.class

pack200 --repack 4Kraft.jar 4Kraft-raw.jar
pack200 -E9 4Kraft.jar.pack.gz 4Kraft.jar
del S.*
cd ..

:quit
