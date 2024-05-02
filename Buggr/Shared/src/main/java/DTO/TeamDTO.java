package DTO;

import Models.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {
    private UUID id;
    private String teamName;
    private String profilePicture;
    private UUID managerId;

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.teamName = team.getTeamName();
        this.profilePicture = team.getProfilePicture();
        this.managerId = team.getManager().getId();
    }

}
