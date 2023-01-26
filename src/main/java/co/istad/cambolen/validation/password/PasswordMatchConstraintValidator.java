package co.istad.cambolen.validation.password;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchConstraintValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String password;
    private String confirmedPassword;
    private String message;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        password = constraintAnnotation.password();
        confirmedPassword = constraintAnnotation.confirmedPassword();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        final Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        final Object confirmedPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmedPassword);

        boolean isValid = false;

        if (passwordValue != null) {
            isValid = passwordValue.equals(confirmedPasswordValue);
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(password)
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(confirmedPassword)
                    .addConstraintViolation();
        }

        return isValid;
    }
}
