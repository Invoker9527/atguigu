package com.invoker.springbootjpa.repository;

import com.invoker.springbootjpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author YangRuiHong
 * @Create 2022-01-30 18:21
 * @Description
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
