package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "stu")
@Data
public class WebProperties {
    private Integer id = 0;
    private String name = "s";
    private Integer id_a = 0;
    private String name_a = "s";
    private Integer id_b = 0;
    private String name_b = "s";
}
