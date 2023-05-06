package br.com.cadastro.exception;


import br.com.cadastro.exception.exceptionhandler.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder{

	@Override
    public Exception decode(String methodKey, Response response) {
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        
        InputStream inputStream = null;
        InputStreamReader isReader = null;
        ErrorResponse er = null;
        StringBuffer sb = new StringBuffer();
        
		try {
			inputStream = responseBody.asInputStream();
			isReader = new InputStreamReader(inputStream);
	        BufferedReader reader = new BufferedReader(isReader);
	        
	        String str;
	        while((str = reader.readLine())!= null){
	           sb.append(str);
	        }
	        
	        er = new ObjectMapper().readValue(sb.toString(), ErrorResponse.class);
	        
	        log.error("ERRO - Retorno statusCode :{}, status :{}, error :{}, message :{}", er.getStatusCode(), er.getStatus(), er.getError(), er.getMessage());
	        
	        if(!er.getErrors().isEmpty()) {
	        	er.setMessage(er.getErrors().get(0));
	        }
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
        if (responseStatus.is5xxServerError() || responseStatus.is4xxClientError()) {
            return new StandErrorException(er);
        } else {
            return new Exception("Erro genérico na aplicação");
        }
    }
}
