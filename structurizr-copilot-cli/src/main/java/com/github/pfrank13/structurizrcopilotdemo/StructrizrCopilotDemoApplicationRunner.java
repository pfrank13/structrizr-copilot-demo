package com.github.pfrank13.structurizrcopilotdemo;

import com.github.pfrank13.structurizrcopilotdemo.copilot.ScriptGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StructrizrCopilotDemoApplicationRunner implements ApplicationRunner {
  private static final Logger LOG = LoggerFactory.getLogger(StructrizrCopilotDemoApplicationRunner.class);

  @Override
  public void run(ApplicationArguments args) throws Exception {
    final var c4FileLocation = args.getOptionValues("c4File").get(0);
    final var outFileLocation = args.getOptionValues("outFile").get(0);
    final var targetSystemName = args.getOptionValues("targetSystemName").get(0);
    final ScriptGenerator scriptGenerator = new ScriptGenerator(c4FileLocation, outFileLocation, targetSystemName);

    scriptGenerator.generate();
  }
}
