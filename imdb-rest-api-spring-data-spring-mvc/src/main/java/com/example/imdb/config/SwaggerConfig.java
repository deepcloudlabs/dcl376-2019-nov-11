package com.example.imdb.config;

import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${build.version}.${build.revision}")
    private String version;

    @Value("${build.timestamp}")
    private long timeStamp;

    @Value("${server.address}")
    private String host;

    @Value("${server.port}")
    private long port;

   
    @Bean
    public Docket api(ServletContext servletContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .host(host.concat(":").concat(Long.toString(port)))
                .pathProvider(new RelativePathProvider(servletContext) {
                    @Override
                    public String getApplicationBasePath() {
                        return "/imdb";
                    }
                }) 
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Imdb Movie Search Service")
                .description("<b>Client FrontEnd API</b><br /><br />Updated: [" + (new Date(timeStamp)).toString() + " ]"
                        + " <script>document.title = \"IMDB MOVIE SEARCH\";"
                        + " document.getElementById('header').remove();"
                        + "</script>")
                .version(version)
                .build();
    }

}