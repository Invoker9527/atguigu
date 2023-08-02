package com.invoker.springbootjpa.repository;

import com.invoker.springbootjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author YangRuiHong
 * @Create 2022-01-30 16:32
 * @Description
 */
@Repository
//继承JpaRepository来完成对数据库的操作（第一个泛型是要操作的实体类，第二个泛型是主键的类型）
public interface UserRepository extends JpaRepository<User, Integer> {
}
