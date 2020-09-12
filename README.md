How to run from the command line via Gradle
```
$ ./gradlew bootRun -Pargs=--outFile=./out.sh,--c4File=./src/test/resources/system.c4,--targetSystemName="Nifty Cool Business"
```
Or alternatively after the jar is built
```
$ java -jar ./build/libs/structrizr-copilot-demo-0.0.1-SNAPSHOT.jar --outFile=./out.sh --c4File=./src/test/resources/system.c4 --targetSystemName="Nifty Cool Business"
```

