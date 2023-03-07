package project.endpoints;

/**
 * @author Pavel Romanov 03.03.2023
 */

public enum Endpoints {
    POSTS("/posts/"),
    USERS("/users/");

    private final String value;

    Endpoints(String value) {
        this.value = value;
    }

    public String getStringValue() {
        return value;
    }
}
