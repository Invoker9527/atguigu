package com.invoker.springbootjpa.entity;

import javax.persistence.*;

/**
 * @Author YangRuiHong
 * @Create 2022-01-30 18:16
 * @Description
 */

@Entity
@Table(name = "tbl_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 255, name = "Name")
    private String name;
    @Column(length = 255, name = "Sid")
    private Integer StudentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentId() {
        return StudentId;
    }

    public void setStudentId(Integer studentId) {
        StudentId = studentId;
    }
}
