package com.github.pfrank13.structrizrcopilotdemo.copilot;

import com.structurizr.Workspace;
import com.structurizr.model.Container;
import com.structurizr.model.Person;
import com.structurizr.model.Relationship;
import com.structurizr.model.SoftwareSystem;
import org.springframework.util.Assert;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Given the goal of only being able to bootstrap one SoftwareSystem we need a way to distinguish which one we are bootsrapping
 * and then look at the others in the diagram and see if they are using the target bootsrapped on by either another SoftwareSystem or Person
 * at a container level to flag that container as needing to be a FrontEnd Service on the AWS Copilot end.
 */
public class CopilotConverter {
  private final String targetSoftwareSystemName;

  public CopilotConverter(final String targetSoftwareSystemName) {
    this.targetSoftwareSystemName = targetSoftwareSystemName;
    afterPropertiesSet();
  }

  private void afterPropertiesSet(){
    Assert.hasText(targetSoftwareSystemName, "No Target Software System defined");
  }

  public Application convert(final Workspace workspace){
    final Set<SoftwareSystem> softwareSystems = workspace.getModel().getSoftwareSystems();
    Assert.isTrue(softwareSystems.size() > 0, "No SoftwareSystem instances found, nothing to do");
    final SoftwareSystem targetSoftwareSystem = findTargetSoftwareSystem(softwareSystems)
        .orElseThrow(() -> new IllegalStateException(String.format("No SoftwareSystem found for description '%s'", targetSoftwareSystemName)));

    final Set<SoftwareSystem> softwareSystemsToExplore = new LinkedHashSet<>(softwareSystems);
    softwareSystemsToExplore.remove(targetSoftwareSystem);

    final Set<Container> containers = targetSoftwareSystem.getContainers();
    Assert.isTrue(containers.size() > 0, "No container instances found, nothing to do");
    final Set<Service> services = new LinkedHashSet<>(containers.size());
    for(Container container : containers){
      final Service.Type serviceType = determineContainerType(container, softwareSystemsToExplore, workspace.getModel().getPeople());
      final Service service = new Service(container.getId(), container.getName(), serviceType);
      services.add(service);
    }
    return new Application(targetSoftwareSystem.getId(), targetSoftwareSystem.getName(), services);
  }

  Optional<SoftwareSystem> findTargetSoftwareSystem(final Set<SoftwareSystem> softwareSystems){
    return softwareSystems.stream().filter(softwareSystem -> softwareSystem.getName().equals(targetSoftwareSystemName)).findFirst();
  }

  Service.Type determineContainerType(final Container targetContainer, final Set<SoftwareSystem> softwareSystems, final Set<Person> persons){
    for(SoftwareSystem softwareSystem : softwareSystems){
      for(Relationship relationship : softwareSystem.getRelationships()){
        if(relationship.getDestinationId().equals(targetContainer.getId())){
          return Service.Type.FRONT_END; //This is an outside system calling this container
        }
      }
    }

    for(Person person : persons){
      for(Relationship relationship : person.getRelationships()){
        if(relationship.getDestinationId().equals(targetContainer.getId())){
          return Service.Type.FRONT_END; //A Person is targeting this container
        }
      }
    }

    return Service.Type.BACK_END; //Nothing seems to target this outside of our SoftwareSystem so this must be a backend service
  }
}
