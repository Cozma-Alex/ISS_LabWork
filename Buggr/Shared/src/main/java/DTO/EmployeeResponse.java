package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private String role;
    private String name;
    private String email;
    private UUID id;
    private UUID accountId;
    private UUID teamId;
    private String profilePicture;
}
