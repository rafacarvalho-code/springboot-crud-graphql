package br.com.cadastro.configurations;

import br.com.cadastro.query.AddressQuery;
import br.com.cadastro.resolver.AddressResolver;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AddressConfig {

    @Value("${viacep.api}")
    protected String viacepHostApi;

    @Value("${viacep.api}")
    protected String apiKey;

    @Bean
    public AddressQuery addressQuery(AddressResolver addressResolver) {
        return new AddressQuery(addressResolver);
    }
}
