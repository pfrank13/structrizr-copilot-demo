How to run from the command line via Gradle
```
$ cd ./structurizr-copilot-cli
$ ../gradlew bootRun -Pargs=--outFile=./out.sh,--c4File=./src/test/resources/system.c4,--targetSystemName="Nifty Cool Business"
```
Or alternatively after the jar is built
```
$ cd ./structurizr-copilot-cli
$ java -jar ./build/libs/structurizr-copilot-cli-0.0.1-SNAPSHOT.jar --outFile=./out.sh --c4File=./src/test/resources/system.c4 --targetSystemName="Nifty Cool Business"
```

