package br.com.femass.ProjetoDS1.infra.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Projeto DS1")
                        .description("Nesta pagina temos todas as rotas para a API do projeto de D1")
                        .version("1.0 - Crud Instituto"));
    }

}
