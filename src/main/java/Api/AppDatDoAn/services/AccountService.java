package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.Account;
import Api.AppDatDoAn.reponsitory.IAccountRepository;
import Api.AppDatDoAn.reponsitory.IRoleRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@NoArgsConstructor
public class AccountService {

    private IAccountRepository accountRepository;
    @Autowired
    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    private IRoleRepository roleRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account getAccountById(UUID id) {
        Optional<Account> optionalUser = accountRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public void addRoleToAccount(UUID accountId, UUID roleId) {
        accountRepository.addRoleToAccount(accountId, roleId);
    }

    public String[] getRolesOfAccount(UUID id) {
        return accountRepository.getRolesOfAccount(id);
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
        UUID userId = accountRepository.getAccountIdByUsername(account.getUsername());
        UUID roleId = roleRepository.getRoleIdByRoleName("USER");
        if (roleId != null && userId != null) {
            accountRepository.addRoleToAccount(userId, roleId);
        }
    }

    public void removeRoleFromAccount(UUID accountId, UUID roleId) {
        accountRepository.removeRoleFromAccount(accountId, roleId);
    }

}
