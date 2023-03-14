package project.endpoints;

/**
 * @author Pavel Romanov 03.03.2023
 */

public enum Endpoints {
    ALL_POSTS("/posts/"),
    POST_BY_ID("/posts/%s"),
    USER_BY_ID("/users/%s"),
    ALL_USERS("/users/");

    private final String value;

    Endpoints(String value) {
        this.value = value;
    }

    public String getStringValue() {
        return value;
    }
}
