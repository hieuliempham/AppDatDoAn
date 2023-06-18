package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Repository
public interface IAccountRepository extends JpaRepository<Account, UUID> {
    @Query("SELECT a FROM Account a WHERE a.username = ?1")
    Account findByUsername(String username);

    @Query("SELECT a FROM Account a WHERE a.verificationCode = ?1")
    public Account findByVerificationCode(String code);

    public Account findByResetPasswordToken(String token);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO account_role (account_id, role_id) " +
            "VALUES (?1, ?2)", nativeQuery = true)
    void addRoleToAccount(UUID accountId, UUID roleId);

    @Query("SELECT a.accountId FROM Account a WHERE a.username = ?1")
    UUID getAccountIdByUsername(String username);

    @Query(value = "SELECT r.role_name FROM Role r INNER JOIN account_role ar " +
            "ON r.role_id = ar.role_id WHERE ar.account_id = ?1", nativeQuery = true)
    String[] getRolesOfAccount(UUID accountId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM account_role WHERE account_id = ?1 AND role_id = ?2", nativeQuery = true)
    void removeRoleFromAccount(UUID accountId, UUID roleId);
}
