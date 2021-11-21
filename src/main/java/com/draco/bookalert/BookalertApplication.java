package com.draco.bookalert;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BookalertApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookalertApplication.class, args);
    }

//    @Bean
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
                jsonConverter.setObjectMapper(new ObjectMapper());
                jsonConverter.setSupportedMediaTypes(Arrays.asList(
                        new MediaType("application", "json", StandardCharsets.UTF_8),
                        new MediaType("text", "javascript", StandardCharsets.UTF_8)));
            }
        }
        return restTemplate;
    }


}
