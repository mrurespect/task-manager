package fsts.mrurespect.backendspring.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserErrorResponce {
    private int status;
    private String message;
}
