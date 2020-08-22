package com.github.pfrank13.structrizrcopilotdemo;

import com.structurizr.dsl.StructurizrDslParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Paths;

@Component
public class StructrizrCopilotDemoCommandLineRunner implements CommandLineRunner {
  private static final Logger LOG = LoggerFactory.getLogger(StructrizrCopilotDemoCommandLineRunner.class);

  @Override
  public void run(final String... args) {
    final File file = Paths.get(args[0]).toFile();
    LOG.info("File Canonical Path={}", file.getAbsolutePath());
    LOG.info("Exists={}", file.exists());

    final StructurizrDslParser structurizrDslParser = new StructurizrDslParser();
  }
}
