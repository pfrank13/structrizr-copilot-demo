package com.github.pfrank13.structrizrcopilotdemo.copilot;

import com.structurizr.Workspace;
import com.structurizr.model.Container;
import com.structurizr.model.SoftwareSystem;
import org.springframework.util.Assert;

import java.util.LinkedHashSet;
import java.util.Set;

public class CopilotConverter {
  public Set<Application> convert(final Workspace workspace){
    final Set<SoftwareSystem> softwareSystems = workspace.getModel().getSoftwareSystems();
    Assert.isTrue(softwareSystems.size() > 0, "No SoftwareSystem instances found, nothing to do");
    final Set<Application> applications = new LinkedHashSet<>(softwareSystems.size());
    for(SoftwareSystem softwareSystem : softwareSystems){
      final Set<Container> containers = softwareSystem.getContainers();
      Assert.isTrue(containers.size() > 0, "No container instances found, nothing to do");
      final Set<Service> services = new LinkedHashSet<>(containers.size());
      for(Container container : containers){
        final Service service = new Service(container.getId(), container.getName());
        services.add(service);
      }
      final Application application = new Application(softwareSystem.getId(), softwareSystem.getName(), services);
      applications.add(application);
    }

    return applications;
  }
}
