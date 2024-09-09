package fsts.mrurespect.backendspring.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<UserErrorResponce> exception(UserException userException){
        UserErrorResponce error=new UserErrorResponce();
        error.setStatus(userException.getStatus().value());
        error.setMessage(userException.getMessage());
        return new ResponseEntity<>(error,userException.getStatus());
    }
}
