package br.com.cadastro.springbootcrudgraphql.resolver;

import br.com.cadastro.client.AddressClient;
import br.com.cadastro.resolver.AddressResolver;
import br.com.cadastro.responses.AddressResponse;
import br.com.cadastro.responses.ViaCepResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
class AddressResolverTest {

    @InjectMocks
    private AddressResolver addressResolver;

    @Mock
    private AddressClient addressClient;

    @Test
    void testGetAddressByZipcodeSuccess() throws Exception {
        // Cria o mock da resposta da API do ViaCEP
        ViaCepResponse mockViaCepResponse = ViaCepResponse.builder()
                .cep("12345678")
                .logradouro("Rua X")
                .bairro("Bairro Y")
                .localidade("Cidade Z")
                .uf("SP")
                .build();

        // Configura o mock do AddressClient para retornar a resposta criada acima
        when(addressClient.getAddressByZipcode(ArgumentMatchers.anyString())).thenReturn(mockViaCepResponse);

        // Chama o método a ser testado
        AddressResponse addressResponse = addressResolver.getAddressByZipcode("12345678", null);

        // Verifica se a resposta está de acordo com o esperado
        assertEquals(HttpStatus.OK.value(), addressResponse.getStatusCode());
        assertEquals("Sucesso", addressResponse.getStatusMessage());
        assertEquals(mockViaCepResponse, addressResponse.getViaCepResponse());
    }

}
