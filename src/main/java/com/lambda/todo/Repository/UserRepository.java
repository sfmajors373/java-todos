package com.lambda.todo.Repository;

import com.lambda.todo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUserid(long id);
    User findByUsername(String name);
    User deleteByUserid(long id);
}
