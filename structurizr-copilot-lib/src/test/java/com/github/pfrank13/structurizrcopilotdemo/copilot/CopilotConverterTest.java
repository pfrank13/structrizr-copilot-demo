package com.github.pfrank13.structurizrcopilotdemo.copilot;

import com.structurizr.Workspace;
import com.structurizr.dsl.StructurizrDslParser;
import com.structurizr.dsl.StructurizrDslParserException;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

class CopilotConverterTest {
  private CopilotConverter copilotConverter;
  private Workspace workspace;

  @BeforeEach
  void setUp() throws IOException, StructurizrDslParserException {
    copilotConverter =  new CopilotConverter("Nifty Cool Business");
    final StructurizrDslParser structurizrDslParser = new StructurizrDslParser();
    final InputStream resource = CopilotConverterTest.class.getResourceAsStream("/system.c4");
    final ByteArrayOutputStream fileContents = new ByteArrayOutputStream();
    IOUtils.copy(resource, fileContents);
    final File tempDslFile = File.createTempFile("c4dsl", "c4");
    IOUtils.write(fileContents.toByteArray(), new FileOutputStream(tempDslFile));

    structurizrDslParser.parse(tempDslFile);
    this.workspace = structurizrDslParser.getWorkspace();
  }

  @Test
  public void convert() {
    final Application application = copilotConverter.convert(workspace);

    Assertions.assertThat(application).isNotNull();
    Assertions.assertThat(application.getServices()).hasSize(2);
    for(Service service : application.getServices()){
      if(service.getName().equals("Web Application")) {
        Assertions.assertThat(service.getType()).isSameAs(Service.Type.FRONT_END);
      }
      if(service.getName().equals("API")){
        Assertions.assertThat(service.getType()).isSameAs(Service.Type.BACK_END);
      }
    }
  }

  @Test
  public void targetSoftwareSystemNotFound(){
    Assertions.assertThatThrownBy(() -> {
      final var copilotConverter = new CopilotConverter("NotFound");
      copilotConverter.convert(workspace);
    }).isInstanceOf(IllegalStateException.class)
        .hasMessage("No SoftwareSystem found for description 'NotFound'")
        .hasNoCause();
  }
}