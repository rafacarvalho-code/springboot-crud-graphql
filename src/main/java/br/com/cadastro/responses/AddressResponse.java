package br.com.cadastro.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {

    private Integer statusCode;
    private String statusMessage;
    private ViaCepResponse viaCepResponse;
}
