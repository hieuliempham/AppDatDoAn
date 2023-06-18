package Api.AppDatDoAn.validator;

import Api.AppDatDoAn.entity.CuaHang;
import Api.AppDatDoAn.validator.annotation.ValidMaCuaHang;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidMaCHValidator implements ConstraintValidator<ValidMaCuaHang, CuaHang> {
    @Override
    public boolean isValid(CuaHang cuaHang, ConstraintValidatorContext context) {
        return cuaHang != null && !cuaHang.getMacuahang().isEmpty();
    }
}
