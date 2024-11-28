package kyungseo.poc.todo.common.base;

import java.time.LocalDateTime;

public class GenericResponseBody {

    private int state;
    private boolean success;
    private String message;
    private String fieldErrors;
    private Object result;
    private LocalDateTime timestamp;

    // Getters and Setters
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(String fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Builder Pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final GenericResponseBody instance = new GenericResponseBody();

        public Builder state(int state) {
            instance.setState(state);
            return this;
        }

        public Builder success(boolean success) {
            instance.setSuccess(success);
            return this;
        }

        public Builder message(String message) {
            instance.setMessage(message);
            return this;
        }

        public Builder fieldErrors(String fieldErrors) {
            instance.setFieldErrors(fieldErrors);
            return this;
        }

        public Builder result(Object result) {
            instance.setResult(result);
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            instance.setTimestamp(timestamp);
            return this;
        }

        public GenericResponseBody build() {
            return instance;
        }
    }
}
