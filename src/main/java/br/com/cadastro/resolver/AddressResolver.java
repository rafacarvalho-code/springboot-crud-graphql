package br.com.cadastro.resolver;

import br.com.cadastro.client.AddressClient;
import br.com.cadastro.exception.StandErrorException;
import br.com.cadastro.exception.exceptionhandler.ErrorResponse;
import br.com.cadastro.responses.AddressResponse;
import br.com.cadastro.responses.ViaCepResponse;
import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AddressResolver implements GraphQLResolver<ViaCepResponse> {

    @Autowired
    private AddressClient addressClient;

    public AddressResponse getAddressByZipcode(String cep, DataFetchingEnvironment env) {
        try {
            ViaCepResponse response = addressClient.getAddressByZipcode(cep);

            return AddressResponse.builder()
                    .statusCode(HttpStatus.OK.value())
                    .statusMessage("Sucesso")
                    .viaCepResponse(response)
                        .build();
        } catch (Exception e) {
            if (e.getCause() instanceof StandErrorException) {
                ErrorResponse er = ((StandErrorException) e.getCause()).getErrorResponse();
                return AddressResponse.builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .statusMessage(er.getMessage())
                            .build();
            }
            return AddressResponse.builder()
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .statusMessage("Erro ao tentar fazer chamada para viacep")
                        .build();
        }
    }
}
