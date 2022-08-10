package ai.overjet.insurance.claims.management.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public enum ClaimProcessingState implements IFieldValue {
    PENDING("Pending"),
    SUCCESS("Success"),
    FAILED("Failed"),
    UNKNOWN("Unknown"),
    PENDING_IMAGE_DOWNLOAD("pending_image_download"),
    PENDING_AI_REVIEW("pending AI Review"),
    PENDING_CLAIM_LIAISON_REVIEW("pending Liaison review"),
    PENDING_DENTAL_CONSULTANT_REVIEW("pending Dental Consultant review"),
    PENDING_RECORD_SPECIALIST_REVIEW("pending Record Specialist review"),
    OVERJET_REVIEW_COMPLETE("Overjet review complete"),
    PENDING_ADE_CONSULTANT_REVIEW("Pending ADE Dental consultant review"),
    PENDING_HUMANA_DENTAL_CONSULTANT_REVIEW("Pending Humana consultant review"),
    READY_FOR_DELIVERY("ready for delivery"),
    OVERJET_REVIEW_PROCESSING(" ojrr2 processing in progress"),
    PENDING_DENTAL_SPECIALITY_REVIEW("pending dental speciality review"),
    DELIVERED_SUCCESSFULLY("delivered successfully");

    private String state;
    public static final String PROCESSING_STATE = "processingState";
    ClaimProcessingState(String state) {
        this.state = state;
    }

    public static Optional<ClaimProcessingState> fromString(String s) {
        for (ClaimProcessingState claimProcessingState : ClaimProcessingState.values()) {
            if (claimProcessingState.state.equalsIgnoreCase(s)) {
                return Optional.of(claimProcessingState);
            }
        }
        return Optional.of(ClaimProcessingState.UNKNOWN);
    }

    /**
     * This method returns ClaimProcessingState, if there is no valid enum found for the given string, PENDING_AI_REVIEW be returned
     * @param name
     * @return ClaimProcessingState
     */
    public static ClaimProcessingState get(String name) {
        try {
            return ClaimProcessingState.valueOf(name);
        }catch (IllegalArgumentException e) {
            log.info("There is no enum matching the string: "+ name);
            return ClaimProcessingState.PENDING_AI_REVIEW;
        }
    }
    @Override
    public String getStringValue() {
        return this.name();
    }
}
