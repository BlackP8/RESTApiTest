package project.models;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Pavel Romanov 03.03.2023
 */

@Data
@Jacksonized
public class User {
    private String id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private Address address;
    private Company company;
}
