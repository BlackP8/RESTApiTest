package project.models;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Pavel Romanov 03.03.2023
 */

@Data
@Jacksonized
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;
}
