package javafx.server.Services;

import DTO.LoginRequest;
import Models.Account;
import Models.Programmer;
import Models.Manager;
import Models.Tester;
import javafx.server.Repositories.RepositoryAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceAccount {

    private final RepositoryAccount repositoryAccount;

    public Account createAccount(Account account) {
        return repositoryAccount.save(account);
    }

    private boolean checkPassword(String plainPassword, String hashedPassword) {
        return Objects.equals(plainPassword, hashedPassword);
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
            var storedPasswordHash = optionalAccount.get().getPassword();
            return checkPassword(loginRequest.getPassword(), storedPasswordHash);
        }
        return false;
    }

    public Account getAccountByEmail(String email) {
        return repositoryAccount.findAccountByEmail(email).orElse(null);
    }

    public String getAccountType(LoginRequest loginRequest) {
        return repositoryAccount.findAccountTypeByEmail(loginRequest.getEmail())
                .orElse("Unknown");
    }

    public Programmer getProgrammer(UUID id) {
        return repositoryAccount.findProgrammerByAccountId(id).orElse(null);
    }

    public Tester getTester(UUID id) {
        return repositoryAccount.findTesterByAccountId(id).orElse(null);
    }

    public Manager getManager(UUID id) {
        return repositoryAccount.findManagerByAccountId(id).orElse(null);
    }

}
