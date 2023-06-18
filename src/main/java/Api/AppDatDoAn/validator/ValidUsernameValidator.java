package Api.AppDatDoAn.validator;

import Api.AppDatDoAn.reponsitory.IAccountRepository;
import Api.AppDatDoAn.validator.annotation.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (accountRepository == null) {
            return true;
        }
        return accountRepository.findByUsername(username) == null;
    }
}
