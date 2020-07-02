package br.com.dudzdev.me;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ LiquibaseProperties.class })
public class MeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeServiceApplication.class, args);
    }

}
