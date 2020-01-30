package com.openpayd.demo.model.dto.output;

public class ExceptionDTO {
    private String message;
    private String code;

    public ExceptionDTO() {
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ExceptionDTO)) {
            return false;
        } else {
            ExceptionDTO other = (ExceptionDTO) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ExceptionDTO;
    }

    public int hashCode() {
        //int PRIME = 3;
        int result = 1;
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "ExceptionDto(message=" + this.getMessage() + ")";
    }
}
