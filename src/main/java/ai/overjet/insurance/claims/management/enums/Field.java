package ai.overjet.insurance.claims.management.enums;

/**
 * Enum for storing the fields that used for various functionalities, to start with, the below fields are used for
 * Rollup logic implementation
 */
public enum Field {
    PROCESSING_STATE("processingState"){
        @Override
        public FieldRuleType getDefaultRollupRule() {
            return FieldRuleType.PROCESSING_STATE_DEFAULT_RULE;
        }
    },
    FAST_TRACK("fastTrack"){
        @Override
        public FieldRuleType getDefaultRollupRule() {
            return FieldRuleType.FAST_TRACK_DEFAULT_RULE;
        }
    },
    INGRESS_TYPE("ingressType"){
        @Override
        public FieldRuleType getDefaultRollupRule() {
            return FieldRuleType.INGRESS_TYPE_DEFAULT_RULE;
        }
    };

    private String fieldName;
    Field(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public FieldRuleType getDefaultRollupRule() {
       throw new UnsupportedOperationException();
    }
}
