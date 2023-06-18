package Api.AppDatDoAn.validator.annotation;

import Api.AppDatDoAn.validator.ValidMaLoaiSPValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidMaLoaiSPValidator.class)
@Documented
public @interface ValidMaLoaiSP {
    String message() default "Vui lòng chọn loại sản phẩm.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
