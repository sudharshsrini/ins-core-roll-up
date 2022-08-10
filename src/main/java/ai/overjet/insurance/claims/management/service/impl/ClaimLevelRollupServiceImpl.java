package ai.overjet.insurance.claims.management.service.impl;


import ai.overjet.insurance.claims.management.enums.Client;
import ai.overjet.insurance.claims.management.enums.Field;
import ai.overjet.insurance.claims.management.enums.FieldRuleType;
import ai.overjet.insurance.claims.management.enums.IFieldValue;
import ai.overjet.insurance.claims.management.service.ClaimLevelRollupService;
import ai.overjet.insurance.claims.management.service.FieldRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ClaimLevelRollupServiceImpl implements ClaimLevelRollupService {
    @Override
    public String performRollup(List<IFieldValue> fieldValues, EnumSet<Client> clientId, Field field) {
        if(clientId == null) {
            log.info("Since ClientId is null, default client rule is executed for rollup logic...");
            clientId = EnumSet.of(Client.ALL_API, Client.ALL_FTP);
        }
        FieldRuleType fieldRuleType = FieldRuleType.getFieldRuleType(clientId,field);
        FieldRule fieldRule = fieldRuleType.getFieldRule();
        fieldRule.setField(field);
        fieldRule.setClient(clientId);
        fieldRule.setFieldValues(fieldValues);
        return fieldRule.execute();
    }
}
