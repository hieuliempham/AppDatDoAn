package Api.AppDatDoAn.validator.annotation;

import Api.AppDatDoAn.validator.ValidMaCHValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidMaCHValidator.class)
@Documented
public @interface ValidMaCuaHang {
    String message() default "Vui lòng chọn cửa hàng.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
