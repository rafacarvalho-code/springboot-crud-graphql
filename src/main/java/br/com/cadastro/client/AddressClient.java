package br.com.cadastro.client;

import br.com.cadastro.configurations.FeignConfig;
import br.com.cadastro.responses.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="addressClient", url = "${viacep.api}", configuration = FeignConfig.class)
public interface AddressClient {

    @GetMapping(path = "/{zipcode}/json/",consumes = MediaType.APPLICATION_JSON_VALUE)
    ViaCepResponse getAddressByZipcode(@PathVariable("zipcode") String zipcode);
}
