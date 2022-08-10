/*
 * Copyright (c) 2021 Overjet, Inc.
 * All rights reserved.
 */

package ai.overjet.insurance.claims.management.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
public enum ServiceLineType implements IFieldValue{
  PTE("PTE"),
  DOS("DOS");

  private String type;
  public static final String SERVICELINE_TYPE = "serviceLineType";
  ServiceLineType(String type) {
    this.type = type;
  }

  public static Optional<ServiceLineType> from(String s) {
    for (ServiceLineType serviceLineType : ServiceLineType.values()) {
      if (serviceLineType.type.equalsIgnoreCase(s)) {
        return Optional.of(serviceLineType);
      }
    }
    return Optional.empty();
  }

  public static Set<ServiceLineType> from(List<String> allSupportedTypes) {
    Set<ServiceLineType> serviceLineTypeSet = new HashSet<>();
    for (ServiceLineType serviceLineType : ServiceLineType.values()) {
      for (String s : allSupportedTypes) {
        if (serviceLineType.type.equalsIgnoreCase(s)) {
          serviceLineTypeSet.add(serviceLineType);
        }
      }
    }
    return serviceLineTypeSet;
  }

  @Override
  public String getStringValue() {
    return this.name();
  }

  /**
   * This method returns ServiceLineType, if there is no valid enum found for the given string, PTE be returned
   * @param name
   * @return ServiceLineType
   */
  public static ServiceLineType get(String name) {
    try {
      return ServiceLineType.valueOf(name);
    }catch (IllegalArgumentException e) {
      log.info("There is no enum matching the string: "+ name);
      return ServiceLineType.PTE;
    }
  }
}
