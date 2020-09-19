package com.github.pfrank13.structurizrcopilotdemo.copilot;

import com.google.common.base.Strings;
import com.google.common.base.Verify;
import com.google.common.collect.ImmutableSet;
import com.structurizr.Workspace;
import com.structurizr.dsl.StructurizrDslParser;
import com.structurizr.dsl.StructurizrDslParserException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Ultimately this will be a GradlePlugin that will dispatch the script lines through the process API in some form or fashion
 */
public class ScriptGenerator {
  private final String c4FileLocation;
  private final String outputFileLocation;
  private final String targetSoftwareSystemName;
  private final ScriptRenderer scriptRenderer;
  private Path c4Path;
  private Path outfilePath;

  public ScriptGenerator(String c4FileLocation, String outputFileLocation, String targetSoftwareSystemName) {
    this(c4FileLocation, outputFileLocation, targetSoftwareSystemName, new ScriptRenderer());
  }

  public ScriptGenerator(String c4FileLocation, String outputFileLocation, String targetSoftwareSystemName, ScriptRenderer scriptRenderer) {
    this.c4FileLocation = c4FileLocation;
    this.outputFileLocation = outputFileLocation;
    this.targetSoftwareSystemName = targetSoftwareSystemName;
    this.scriptRenderer = scriptRenderer;
    afterPropertiesSet();
  }

  private void afterPropertiesSet(){
    Verify.verify(!Strings.isNullOrEmpty(c4FileLocation), "File Location cannot be empty");
    Verify.verify(!Strings.isNullOrEmpty(targetSoftwareSystemName), "TargetSoftwareSystemName cannot be empty");
    Verify.verify(!Strings.isNullOrEmpty(outputFileLocation), "outputFileLocation cannot be empty");
    Verify.verifyNotNull(scriptRenderer, "ScritRenderer cannot be null");
    this.c4Path = Path.of(c4FileLocation);
    this.outfilePath = Path.of(outputFileLocation);
  }

  public void generate() throws StructurizrDslParserException, IOException {
    final StructurizrDslParser structurizrDslParser = new StructurizrDslParser();
    final File c4File = c4Path.toFile();
    structurizrDslParser.parse(c4File);

    final Workspace workspace = structurizrDslParser.getWorkspace();

    final CopilotConverter copilotConverter = new CopilotConverter(targetSoftwareSystemName);
    final Application application = copilotConverter.convert(workspace);

    final List<String> lines = scriptRenderer.render(application, ImmutableSet.of(new Environment("test")));

    final File outFile = this.outfilePath.toFile();
    try(final var writer = new FileWriter(outFile)){
      for(var line : lines){
        writer.write(line);
      }
    }
  }
}
