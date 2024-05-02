package spring.server.Services;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.server.API.DTO.LoginRequest;
import spring.server.Models.Account;
import spring.server.Repositories.RepositoryAccount;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceAccount {

    private final RepositoryAccount repositoryAccount;

    public Account createAccount(Account account) {
        return repositoryAccount.save(account);
    }

    public Account getAccount(UUID id) {
        return repositoryAccount.findById(id).orElse(null);
    }

    public void updateAccount(UUID id, Account account) {
        Account accountToUpdate = repositoryAccount.findById(id).orElse(null);
        if (accountToUpdate != null) {
            accountToUpdate.setEmail(account.getEmail());
            accountToUpdate.setProfilePicture(account.getProfilePicture());
            accountToUpdate.setPassword(account.getPassword());
            repositoryAccount.save(accountToUpdate);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }

    public void deleteAccount(UUID id) {
        repositoryAccount.deleteById(id);
    }

    public Iterable<Account> getAllAccounts() {
        return repositoryAccount.findAll();
    }

    public boolean login(LoginRequest loginRequest) {
        var optionalAccount = repositoryAccount.findAccountByEmail(loginRequest.getEmail());
        if (optionalAccount.isPresent()) {
            var password = hashPasswords(loginRequest.getPassword());
            return password.equals(optionalAccount.get().getPassword());
        }
        return false;
    }

    private String hashPasswords(String password) {
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public String getAccountType(LoginRequest loginRequest) {
        return repositoryAccount.findAccountTypeByEmail(loginRequest.getEmail())
                .orElse("Unknown");
    }
}
