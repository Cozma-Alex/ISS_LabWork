package javafx.server.API.Controllers;

import DTO.EmployeeResponse;
import DTO.LoginRequest;
import javafx.server.Services.ServiceAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class ControllerAccount {

    @Autowired
    private ServiceAccount serviceAccount;

    @PostMapping("/login")
    public ResponseEntity<EmployeeResponse> login(@RequestBody LoginRequest loginRequest) {
        if (serviceAccount.login(loginRequest)) {
            var accountType = serviceAccount.getAccountType(loginRequest);
            var employeeResponse = new EmployeeResponse();
            employeeResponse.setRole(accountType);
            var account = serviceAccount.getAccountByEmail(loginRequest.getEmail());
            employeeResponse.setEmail(account.getEmail());
            employeeResponse.setAccountId(account.getId());
            employeeResponse.setProfilePicture(account.getProfilePicture());
            switch (accountType) {
                case "Programmer":
                    var programmer = serviceAccount.getProgrammer(account.getId());
                    employeeResponse.setName(programmer.getName());
                    employeeResponse.setTeamId(programmer.getTeam().getId());
                    employeeResponse.setId(programmer.getId());
                    break;
                case "Tester":
                    var tester = serviceAccount.getTester(account.getId());
                    employeeResponse.setName(tester.getName());
                    employeeResponse.setTeamId(tester.getTeam().getId());
                    employeeResponse.setId(tester.getId());
                    break;
                case "Manager":
                    var manager = serviceAccount.getManager(account.getId());
                    employeeResponse.setName(manager.getName());
                    employeeResponse.setTeamId(null);
                    employeeResponse.setId(manager.getId());
                    break;
            }
            return ResponseEntity.ok(employeeResponse);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
