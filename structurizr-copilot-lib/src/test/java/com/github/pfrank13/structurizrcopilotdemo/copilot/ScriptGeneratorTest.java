package com.github.pfrank13.structurizrcopilotdemo.copilot;

import com.structurizr.dsl.StructurizrDslParserException;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


class ScriptGeneratorTest {
  private File c4File;
  private File outFile;
  private List<String> expectedLines;

  @BeforeEach
  void setUp() throws IOException {
    final var c4Resource = ScriptGeneratorTest.class.getResource("/system.c4");
    final var expectedResource = ScriptGeneratorTest.class.getResource("/expected.sh");
    this.expectedLines = FileUtils.readLines(new File(expectedResource.getFile()), StandardCharsets.UTF_8);
    c4File = new File(c4Resource.getFile());
    outFile = File.createTempFile("c4dsl", "c4");
  }

  @Test
  void generate() throws IOException, StructurizrDslParserException {
    final ScriptGenerator scriptGenerator = new ScriptGenerator(c4File.getPath(), outFile.getPath(), "Nifty Cool Business");
    scriptGenerator.generate();

    final List<String> renderedLines = FileUtils.readLines(outFile, StandardCharsets.UTF_8);
    Assertions.assertThat(renderedLines).containsExactlyInAnyOrderElementsOf(expectedLines);
  }
}