package br.com.cadastro.exception.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
	
	private Integer statusCode;
	
    private Integer status;
    
    private Integer error;
    
    private String message;
    
    private String path;
    
    private List<String> errors = new ArrayList<>();
    
}
