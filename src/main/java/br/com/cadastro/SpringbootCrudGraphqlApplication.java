package br.com.cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@ComponentScan(basePackages = "br.com.cadastro")
@SpringBootApplication
public class SpringbootCrudGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudGraphqlApplication.class, args);
	}
}


