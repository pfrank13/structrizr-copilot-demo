package com.github.pfrank13.structurizrcopilotdemo.copilot;

import java.util.Objects;

public class Service {
  private final String id;
  private final String name;
  private final Type type;

  public Service(final String id,
                 final String name,
                 final Type type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Type getType() {
    return type;
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

  public enum Type {
    FRONT_END("Load Balanced Web Service"),
    BACK_END("Backend Service");

    private final String copilotCliValue;

    Type(final String copilotCliValue) {
      this.copilotCliValue = copilotCliValue;
    }

    public String getCopilotCliValue() {
      return copilotCliValue;
    }
  }
}
