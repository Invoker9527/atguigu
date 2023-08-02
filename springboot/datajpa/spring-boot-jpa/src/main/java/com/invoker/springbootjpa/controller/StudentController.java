package com.invoker.springbootjpa.controller;

import com.invoker.springbootjpa.entity.Student;
import com.invoker.springbootjpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author YangRuiHong
 * @Create 2022-01-30 18:23
 * @Description
 */
@Order(0)
@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/stu/{id}")
    public Student getStudent(Integer id) {
        return studentRepository.findById(id).get();
    }

    @GetMapping("/stu")
    public Student insert(Student student) {
        return studentRepository.save(student);
    }
}
