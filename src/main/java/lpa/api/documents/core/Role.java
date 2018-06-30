package lpa.api.documents.core;

public enum Role {
    ADMIN, MANAGER, OPERATOR, REGISTERED, ANONYMOUS;

    public String roleName() {
        return "ROLE_" + this.toString();
    }

}
