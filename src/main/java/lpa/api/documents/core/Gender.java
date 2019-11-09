package lpa.api.documents.core;

public enum Gender {
    MALE, FEMALE, UNKNOWN;

    public String gender() {
        return this.toString();
    }
}
