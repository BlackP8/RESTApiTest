package project.models;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Pavel Romanov 03.03.2023
 */

@Data
@Jacksonized
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
}
