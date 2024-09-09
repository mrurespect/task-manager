package fsts.mrurespect.backendspring.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class UserException extends RuntimeException{
    private HttpStatus status;
    public UserException(String message,HttpStatus status) {
        super(message);
        this.status=status;

    }

}
