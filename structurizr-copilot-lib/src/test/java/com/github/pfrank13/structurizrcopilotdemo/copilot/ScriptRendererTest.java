package com.github.pfrank13.structurizrcopilotdemo.copilot;

import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ScriptRendererTest {
  private ScriptRenderer scriptRenderer;

  @BeforeEach
  void setUp() {
    scriptRenderer = new ScriptRenderer();
  }

  @Test
  void render() {
    final Service webApplication = new Service("2", "Web Application", Service.Type.FRONT_END);
    final Service apiApplication = new Service("3", "API", Service.Type.BACK_END);
    final Application application = new Application("1", "Nifty Cool Business", ImmutableSet.of(webApplication, apiApplication));
    final Environment environment = new Environment("test");
    final List<String> lines = scriptRenderer.render(application, ImmutableSet.of(environment));
    for(var line : lines){
      System.out.print(line);
    }
  }

  String formatName(final String name){
    return name.toLowerCase(Locale.US).replace(" ", "-");
  }
}