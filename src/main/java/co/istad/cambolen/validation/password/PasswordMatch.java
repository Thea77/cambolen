package co.istad.cambolen.validation.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchConstraintValidator.class)
@Documented
public @interface PasswordMatch {

    String message() default "Passwords do not match!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String password();
    String confirmedPassword();

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        PasswordMatch[] value();
    }

}
