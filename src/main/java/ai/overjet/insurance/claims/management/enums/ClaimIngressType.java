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
public enum ClaimIngressType implements IFieldValue{
  INITIAL("INITIAL"),
  RFI("RFI"),
  APPEAL("APPEAL");

  private String type;
  public static final String INGRESS_TYPE = "ingressType";

  ClaimIngressType(String type) {
    this.type = type;
  }

  public static Optional<ClaimIngressType> from(String s) {
    for (ClaimIngressType claimType : ClaimIngressType.values()) {
      if (claimType.type.equalsIgnoreCase(s)) {
        return Optional.of(claimType);
      }
    }
    return Optional.empty();
  }

  public static Set<ClaimIngressType> from(List<String> allSupportedTypes) {
    Set<ClaimIngressType> ingressTypeSet = new HashSet<>();
    for (ClaimIngressType ingressType : ClaimIngressType.values()) {
      for (String s : allSupportedTypes) {
        if (ingressType.type.equalsIgnoreCase(s)) {
          ingressTypeSet.add(ingressType);
        }
      }
    }
    return ingressTypeSet;
  }

  public boolean isInitial(){
    return this == INITIAL;
  }

  public boolean isRfi(){
    return this == RFI;
  }

  public boolean isAppeal(){
    return this == APPEAL;
  }

  @Override
  public String getStringValue() {
    return this.name();
  }

  /**
   * This method returns ClaimIngressType, if there is no valid enum found for the given string, INITIAL be returned
   * @param name
   * @return ClaimIngressType
   */
  public static ClaimIngressType get(String name) {
    try {
      return ClaimIngressType.valueOf(name);
    }catch (IllegalArgumentException e) {
      log.info("There is no enum matching the string: "+ name);
      return ClaimIngressType.INITIAL;
    }
  }
}
