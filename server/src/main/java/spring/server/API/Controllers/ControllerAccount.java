package spring.server.API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import spring.server.API.DTO.LoginRequest;
import spring.server.API.DTO.UpdateMessage;
import spring.server.Services.ServiceAccount;

@RestController
@RequestMapping("/account")
public class ControllerAccount {

    @Autowired
    private ServiceAccount serviceAccount;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        if (serviceAccount.login(loginRequest)) {
            var type = serviceAccount.getAccountType(loginRequest);

            messagingTemplate.convertAndSendToUser(loginRequest.getEmail(), "/topic/updates", new UpdateMessage("Welcome!"));

            return ResponseEntity.ok(type);
        } else {
            return ResponseEntity.badRequest().body("Login failed");
        }
    }
}
