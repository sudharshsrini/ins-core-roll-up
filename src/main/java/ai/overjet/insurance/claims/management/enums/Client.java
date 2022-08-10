package ai.overjet.insurance.claims.management.enums;

/**
 * Enum constants to store the clients info for static use. Holds info like, if the client is API/FTP,
 * clientId, clientIdString etc
 */
public enum Client {
    ALL_API("1000","all_api","API"),
    ALL_FTP("1001","all_ftp","FTP"),
    GUARDIAN("2","glic", "API"),
    AMERITAS("7","ameritas", "FTP"),
    HUMANA("8", "humana", "FTP"),
    METLIFE("10", "metlife", "FTP"),
    ELEVENCE_HEALTH("21", "elevence", "API");

    private String clientId;
    private String clientIdString;
    private String communicationType;

    Client(String clientId, String clientIdString, String communicationType) {
        this.clientId = clientId;
        this.clientIdString = clientIdString;
        this.communicationType = communicationType;
    }

    public String getCommunicationType() {
        return communicationType;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientIdString() {
        return clientIdString;
    }
}
