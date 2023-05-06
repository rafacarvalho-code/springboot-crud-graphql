package br.com.cadastro.query;

import br.com.cadastro.resolver.AddressResolver;
import br.com.cadastro.responses.AddressResponse;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;

public class AddressQuery implements GraphQLQueryResolver {

    private AddressResolver addressResolver;

    public AddressQuery(AddressResolver addressResolver) {
        this.addressResolver = addressResolver;
    }

    public AddressResponse getAddressByZipcode(String cep, DataFetchingEnvironment env) {
        return this.addressResolver.getAddressByZipcode(cep, env);
    }
}
