package com.github.pfrank13.structrizrcopilotdemo.copilot;

import java.util.Objects;

public class Service {
  private final String id;
  private final String name;

  public Service(final String id,
                 final String name) {
    this.id = id;
    this.name = name;
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
    Service service = (Service) o;
    return id.equals(service.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
