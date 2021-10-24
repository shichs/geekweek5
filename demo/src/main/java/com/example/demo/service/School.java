package com.example.demo.service;

import com.example.demo.bean.Klass;
import com.example.demo.bean.Student;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Data
@Service
public class School implements ISchool {

    @Resource
    Klass klass;
    
    @Resource
    Student student;
    
    @Override
    public void ding(){
        for (Student stu : klass.getStudents()) {
            System.out.println(stu.toString());
        }
        System.out.println("Class1 have " + this.klass.getStudents().size() + " students and one is " + this.student);
    }
    
}
