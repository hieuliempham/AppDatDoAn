package Api.AppDatDoAn.validator;

import Api.AppDatDoAn.entity.LoaiSanPham;
import Api.AppDatDoAn.validator.annotation.ValidMaLoaiSP;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidMaLoaiSPValidator implements ConstraintValidator<ValidMaLoaiSP, LoaiSanPham> {
    @Override
    public boolean isValid(LoaiSanPham loaiSanPham, ConstraintValidatorContext context) {
        return loaiSanPham != null && loaiSanPham.getMaloai() != null;
    }
}
