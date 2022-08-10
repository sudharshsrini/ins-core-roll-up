package ai.overjet.insurance.claims.management.service;

import ai.overjet.insurance.claims.management.enums.Client;
import ai.overjet.insurance.claims.management.enums.Field;
import ai.overjet.insurance.claims.management.enums.IFieldValue;

import java.util.EnumSet;
import java.util.List;

public interface ClaimLevelRollupService {
    String performRollup(List<IFieldValue> fieldValues, EnumSet<Client> clientId, Field field);
}
