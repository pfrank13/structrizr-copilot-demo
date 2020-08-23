package com.github.pfrank13.structrizrcopilotdemo.copilot;

import com.structurizr.Workspace;
import com.structurizr.dsl.StructurizrDslParser;
import com.structurizr.dsl.StructurizrDslParserException;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CopilotConverterTest {
  private CopilotConverter copilotConverter;

  @BeforeEach
  void setUp() {
    copilotConverter =  new CopilotConverter();
  }

  @Test
  void convert() throws IOException, StructurizrDslParserException {
    final StructurizrDslParser structurizrDslParser = new StructurizrDslParser();
    final Resource resource = new ClassPathResource("system.c4");
    final byte[] fileContents = FileCopyUtils.copyToByteArray(new BufferedInputStream(resource.getInputStream()));
    final File tempDslFile = File.createTempFile("c4dsl", "c4");
    IOUtils.write(fileContents, new FileOutputStream(tempDslFile));

    structurizrDslParser.parse(tempDslFile);
    final Workspace workspace = structurizrDslParser.getWorkspace();

    final Set<Application> applications = copilotConverter.convert(workspace);

    Assertions.assertThat(applications).hasSize(1);
    for(Application application : applications){
      Assertions.assertThat(application.getServices()).hasSize(2);
    }
  }
}