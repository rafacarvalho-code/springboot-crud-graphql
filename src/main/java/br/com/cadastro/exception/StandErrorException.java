package br.com.cadastro.exception;

import br.com.cadastro.exception.exceptionhandler.ErrorResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StandErrorException extends Exception {

    private ErrorResponse errorResponse;

    public StandErrorException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
