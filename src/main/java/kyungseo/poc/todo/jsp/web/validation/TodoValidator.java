package kyungseo.poc.todo.jsp.web.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kyungseo.poc.todo.jsp.payload.TodoDto;

@Component
public class TodoValidator implements Validator {

    @Override
    public boolean supports(final Class<?> clazz) { // 제네릭 타입 명시
        return TodoDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "field.required");
    }
}
