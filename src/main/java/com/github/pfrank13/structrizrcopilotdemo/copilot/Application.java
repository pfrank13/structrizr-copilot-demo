package com.github.pfrank13.structrizrcopilotdemo.copilot;

import java.util.Objects;
import java.util.Set;

public class Application {
  private final String id;
  private final String name;
  private final Set<Service> services;

  public Application(final String id,
                     final String name,
                     final Set<Service> services) {
    this.id = id;
    this.name = name;
    this.services = services;
  }

  public Set<Service> getServices() {
    return services;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Application that = (Application) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
