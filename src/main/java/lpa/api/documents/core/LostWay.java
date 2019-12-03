package lpa.api.documents.core;

public enum LostWay {
    LOST_FROM_HOME, LOST_FROM_PARK, LOST_STOLEN, LOST_FROM_STREET, OTHER;

    public String lostWayName() {
        return "Lost Way " + this.toString();
    }
}
