# springboot-crud-graphql-api


## Basic Query

1. Get Addres by zipcode 

```
getAddressByZipcode

query ($cep: String!) {
    getAddressByZipcode(cep: $cep) {
        statusCode
        statusMessage
        viaCepResponse {
            cep
            logradouro
            complemento
            bairro
            localidade
            uf
        }
    }
}

```
```
Ex: Response
{
    "data": {
        "getAddressByZipcode": {
            "statusCode": 200,
            "statusMessage": "Sucesso",
            "viaCepResponse": {
                "cep": "01311-100",
                "logradouro": "Avenida Paulista ",
                "complemento": "",
                "bairro": "Bela Vista",
                "localidade": "São Paulo",
                "uf": "SP"
            }
        }
    }
}
```
## Lincense
copyright: Rafael Carvalho