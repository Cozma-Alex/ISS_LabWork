package javafx.server.Repositories;

import Models.Account;
import Models.Programmer;
import Models.Manager;
import Models.Tester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositoryAccount extends JpaRepository<Account, UUID> {

    @Query("SELECT a FROM Account a WHERE a.email = ?1")
    Optional<Account> findAccountByEmail(String email);

    @Query("SELECT " + "CASE " + "WHEN p.id IS NOT NULL THEN 'Programmer' " + "WHEN t.id IS NOT NULL THEN 'Tester' " + "WHEN m.id IS NOT NULL THEN 'Manager' " + "ELSE 'Unknown' " + "END " + "FROM " + "Account a " + "LEFT JOIN " + "Programmer p ON a.id = p.account.id " + "LEFT JOIN " + "Tester t ON a.id = t.account.id " + "LEFT JOIN " + "Manager m ON a.id = m.account.id " + "WHERE " + "a.email = ?1")
    Optional<String> findAccountTypeByEmail(String email);

    @Query("SELECT p FROM Programmer p WHERE p.account.id = ?1")
    Optional<Programmer> findProgrammerByAccountId(UUID accountId);

    @Query("SELECT t FROM Tester t WHERE t.account.id = ?1")
    Optional<Tester> findTesterByAccountId(UUID accountId);

    @Query("SELECT m FROM Manager m WHERE m.account.id = ?1")
    Optional<Manager> findManagerByAccountId(UUID accountId);

}