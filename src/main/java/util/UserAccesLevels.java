package util;

public enum UserAccesLevels {
    STUDANT("studant"),
    PROFESSOR("professor");

    private final String accessLevel;

    UserAccesLevels(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAccessLevel() {
        return accessLevel;
    }
}
