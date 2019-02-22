package com.lambda.todo.Repository;

import com.lambda.todo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long>
{
    Todo findByTodoid(long id);

    @Query(value = " SELECT t.todoid, t.description FROM todo t, user u WHERE user.username = name", nativeQuery = true)
    List<Object[]> todosByusername(String name);

    Todo deleteByTodoid(long id);
}
