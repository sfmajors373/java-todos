package com.lambda.todo.Repository;

import com.lambda.todo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long>
{
    Todo findByTodoid(long id);

    @Query(value = " SELECT t.todoid, t.description, u.userid, u.username FROM todo t, users u WHERE t.userid = u.userid", nativeQuery = true)
    List<Object[]> todosByusername();

    Todo deleteByTodoid(long id);
}
