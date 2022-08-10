package ai.overjet.insurance.claims.management.enums;

import ai.overjet.insurance.claims.management.service.FieldRule;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * Roll-up logic rules by field and client. To start with the rules are generic for all clients.
 * In case in future we need separate logic for a client for rollup, add a new rule for the client
 */
public enum FieldRuleType {
    PROCESSING_STATE_DEFAULT_RULE(Field.PROCESSING_STATE, EnumSet.of(Client.ALL_API, Client.ALL_FTP)){
        @Override
        public FieldRule getFieldRule() {
            return new FieldRule.GenericProcessingStateFieldRollupRule();
        }

        @Override
        public List<ClaimProcessingState> getPrecedenceList() {
            return Arrays.asList(new ClaimProcessingState[] {
                    ClaimProcessingState.PENDING_AI_REVIEW, ClaimProcessingState.OVERJET_REVIEW_PROCESSING,
                    ClaimProcessingState.OVERJET_REVIEW_COMPLETE, ClaimProcessingState.PENDING_ADE_CONSULTANT_REVIEW,
                    ClaimProcessingState.PENDING_CLAIM_LIAISON_REVIEW, ClaimProcessingState.PENDING_DENTAL_CONSULTANT_REVIEW,
                    ClaimProcessingState.PENDING_HUMANA_DENTAL_CONSULTANT_REVIEW, ClaimProcessingState.PENDING_RECORD_SPECIALIST_REVIEW,
                    ClaimProcessingState.READY_FOR_DELIVERY, ClaimProcessingState.DELIVERED_SUCCESSFULLY
            });
        }
    },
    FAST_TRACK_DEFAULT_RULE(Field.FAST_TRACK, EnumSet.of(Client.AMERITAS)) {
        @Override
        public FieldRule getFieldRule() {
            return new FieldRule.GenericFastTrackFieldRollupRule();
        }
    },
    INGRESS_TYPE_DEFAULT_RULE(Field.INGRESS_TYPE, EnumSet.of(Client.ALL_API, Client.ALL_FTP)){
        @Override
        public FieldRule getFieldRule() {
            return new FieldRule.GenericIngressTypeFieldRollupRule();
        }
    };

    public static FieldRuleType getFieldRuleType(EnumSet<Client> clientIds, Field field) {
        FieldRuleType[] fieldRuleTypes = FieldRuleType.values();

        for(FieldRuleType fieldRuleType: fieldRuleTypes) {
            EnumSet<Client> clientList = fieldRuleType.getClients();

            if(fieldRuleType.field == field && (clientList.stream().anyMatch(clientIds::contains)
                    || clientList.contains(Client.ALL_API) || clientList.contains(Client.ALL_FTP))){
                return fieldRuleType;
            }
        }
        //If client Ids does not match
        return  field.getDefaultRollupRule();
    }

    public Field getField() {
        return field;
    }

    public EnumSet<Client> getClients() {
        return clients;
    }

    public FieldRule getFieldRule() {
        throw new UnsupportedOperationException();
    }

    /**
     * This method gives list of field values in order of precedence to be assigned at the claim level for roll-up logic
     * This is designed to have a precedence list per client in case the order or specific value changes need to be accomodated.
     * @return
     */
    public List<? extends IFieldValue> getPrecedenceList() {
        throw new UnsupportedOperationException();
    }
    private Field field;
    private EnumSet<Client> clients;
    FieldRuleType(Field field, EnumSet<Client> clients) {
        this.field = field;
        this.clients = clients;
    }
}
