package project.models;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Pavel Romanov 02.03.2023
 */

@Data
@Jacksonized
@Builder
public class Message {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
}
