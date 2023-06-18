package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.Account;
import Api.AppDatDoAn.entity.CustomUserDetail;
import Api.AppDatDoAn.reponsitory.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null)
            throw new UsernameNotFoundException("User not found");
        return new CustomUserDetail(account, accountRepository);
    }
}
