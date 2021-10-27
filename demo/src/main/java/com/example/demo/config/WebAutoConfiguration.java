package com.example.demo.config;

import com.example.demo.bean.Klass;
import com.example.demo.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(WebProperties.class)
public class WebAutoConfiguration {

    @Autowired
    WebProperties properties;

    @Bean
    public Klass creatKlass() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(properties.getId(), properties.getName()));
        students.add(new Student(properties.getId_a(), properties.getName_a()));
        students.add(new Student(properties.getId_b(), properties.getName_b()));
        return new Klass(students);
    }

//    @Bean
//    public Student creatStudent() {
//        return new Student(properties.getId(), properties.getName());
//    }

}
