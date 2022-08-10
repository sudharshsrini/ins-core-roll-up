package ai.overjet.insurance.claims.management.service;

import ai.overjet.insurance.claims.management.enums.Client;
import ai.overjet.insurance.claims.management.enums.Field;
import ai.overjet.insurance.claims.management.enums.FieldRuleType;
import ai.overjet.insurance.claims.management.enums.IFieldValue;
import lombok.Data;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * This class allows to add specific logic to a rollup rule type. As of now,Just the generic rules are available for each rollup field
 */
@Data
public abstract class FieldRule {
    private Field field;
    private EnumSet<Client> client;
    private List<IFieldValue> fieldValues;
    public abstract String execute();

    public static class GenericProcessingStateFieldRollupRule extends FieldRule {
        @Override
        public String execute() {
            List<? extends IFieldValue> claimProcessingStates = FieldRuleType.PROCESSING_STATE_DEFAULT_RULE.getPrecedenceList();
            for (IFieldValue claimProcessingState: claimProcessingStates) {
                for(IFieldValue runTimeFieldValue: getFieldValues()) {
                    if(runTimeFieldValue == claimProcessingState) {
                        return claimProcessingState.getStringValue();
                    }
                }
            }
            throw new UnsupportedOperationException("None of the processing states matched values in the precedence list..");
        }
    }

    public static class GenericFastTrackFieldRollupRule extends FieldRule {
        @Override
        public String execute() {
            return null;
        }
    }

    public static class GenericIngressTypeFieldRollupRule extends FieldRule {
        @Override
        public String execute() {
            return null;
        }
    }
}
