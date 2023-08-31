package com.example.cloudSpanner;

import com.example.cloudSpanner.service.BootstrapService;
import com.google.cloud.spring.data.spanner.repository.config.EnableSpannerRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableSpannerRepositories
public class CloudSpannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudSpannerApplication.class, args);
    }

}


