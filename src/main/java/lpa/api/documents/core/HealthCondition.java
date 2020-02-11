package lpa.api.documents.core;

public enum HealthCondition {

    INJURED, HEALTHY, SICK, OTHER;

    public String healthConditionName() {
        return "HEALTH_CONDITION_" + this.toString();
    }

}