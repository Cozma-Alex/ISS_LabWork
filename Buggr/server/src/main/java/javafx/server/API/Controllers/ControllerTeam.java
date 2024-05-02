package javafx.server.API.Controllers;

import DTO.TeamDTO;
import javafx.server.Services.ServiceTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/team")
public class ControllerTeam {

    @Autowired
    private ServiceTeam serviceTeam;

    @GetMapping("/manager/{id}")
    public ResponseEntity<ArrayList<TeamDTO>> getTeamsByManager(@PathVariable UUID id) {
        return ResponseEntity.ok(serviceTeam.getTeamsByManager(id));
    }

}
