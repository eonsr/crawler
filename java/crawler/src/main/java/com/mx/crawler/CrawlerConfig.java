package com.mx.crawler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Clase de configuracion del proyecto
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Configuration
@PropertySource("classpath:crawler.properties")
@EnableSwagger2
public class CrawlerConfig {

	/**
	 * Metodo requerido para configuracion
	 * 
	 * @return builder para swagger 
	 */
	@Bean
    public Docket api() {
		
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();
        
    }
	
}
