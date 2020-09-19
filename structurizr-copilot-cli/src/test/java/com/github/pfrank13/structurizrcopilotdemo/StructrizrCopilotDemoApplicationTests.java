package com.github.pfrank13.structurizrcopilotdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args = {"--outFile=./out.sh","--c4File=./src/test/resources/system.c4","--targetSystemName=Nifty Cool Business"})
class StructrizrCopilotDemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
