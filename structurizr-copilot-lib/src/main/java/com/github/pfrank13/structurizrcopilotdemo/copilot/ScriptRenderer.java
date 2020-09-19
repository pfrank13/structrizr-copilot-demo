package com.github.pfrank13.structurizrcopilotdemo.copilot;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ScriptRenderer {
  public static final String SHEBANG = "#!/usr/bin/env bash\n";
  public static final String APP_TEMPLATE = "copilot app init \"%s\"\n";
  public static final String ENV_TEMPLATE = "copilot env init --name \"%s\" --profile default\n";
  public static final String SERVICE_INIT_TEMPLATE = "copilot svc init -d ./Dockerfile --name \"%s\" -t \"%s\"\n";
  public static final String SERVICE_DEPLOY_TEMPLATE = "copilot svc deploy --name \"%s\" --env \"%s\"\n";

  public List<String> render(final Application application, final Set<Environment> environments){
    final ImmutableList.Builder<String> lines = ImmutableList.builder();
    lines.add(SHEBANG, String.format(APP_TEMPLATE, formatName(application.getName())));
    for(var environment : environments){
      lines.add(String.format(ENV_TEMPLATE, formatName(environment.getName())));
      for(var service : application.getServices()){
        lines.add(String.format(SERVICE_INIT_TEMPLATE, formatName(service.getName()), service.getType().getCopilotCliValue()));
        lines.add(String.format(SERVICE_DEPLOY_TEMPLATE, formatName(service.getName()), formatName(environment.getName())));
      }
    }

    return lines.build();
  }

  String formatName(final String name){
    return name.toLowerCase(Locale.US).replace(" ", "-");
  }
}
