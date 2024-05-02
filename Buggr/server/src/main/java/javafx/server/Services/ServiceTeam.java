package javafx.server.Services;

import DTO.TeamDTO;
import Models.Team;
import javafx.server.Repositories.RepositoryTeam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceTeam {

    private final RepositoryTeam repositoryTeam;

    public void deleteTeam(UUID id) {
        repositoryTeam.deleteById(id);
    }

    public Iterable<Team> getAllTeams() {
        return repositoryTeam.findAll();
    }

    public Team getTeam(UUID id) {
        return repositoryTeam.findById(id).orElse(null);
    }

    public Team createTeam(Team team) {
        return repositoryTeam.save(team);
    }

    public void updateTeam(UUID id, Team team) {
        Team teamToUpdate = repositoryTeam.findById(id).orElse(null);
        if (teamToUpdate != null) {
            teamToUpdate.setTeamName(team.getTeamName());
            teamToUpdate.setProfilePicture(team.getProfilePicture());
            repositoryTeam.save(teamToUpdate);
        } else {
            throw new IllegalArgumentException("Team not found");
        }
    }

    public ArrayList<TeamDTO> getTeamsByManager(UUID id) {
        ArrayList<Team> teams = repositoryTeam.findByManagerId(id);
        ArrayList<TeamDTO> teamDTOs = new ArrayList<>();
        for (Team team : teams) {
            teamDTOs.add(new TeamDTO(team));
        }
        return teamDTOs;
    }
}
