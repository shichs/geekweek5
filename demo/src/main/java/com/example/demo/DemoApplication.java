package com.example.demo;

import com.example.demo.bean.Student;
import com.example.demo.service.ISchool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import starter.StarterProperties;

import javax.annotation.Resource;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Resource
    private StarterProperties starterProperties;

    @Bean
    public Student creatStuBean() {
        final Student student = new Student();
        student.setId(starterProperties.getId());
        student.setName(starterProperties.getName());
        System.out.println(student);
        return student;
    }



    @Resource
    private ISchool school;

    @Bean
    public void creatBean() {
        school.ding();
    }
}
