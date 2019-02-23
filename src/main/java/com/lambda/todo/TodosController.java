package com.lambda.todo;

import com.lambda.todo.Repository.TodoRepository;
import com.lambda.todo.Repository.UserRepository;
import com.lambda.todo.models.Todo;
import com.lambda.todo.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Api(value="Todos Application", description="Todos sprint challenge")
@RestController
@RequestMapping(path={}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodosController
{
    @Autowired
    TodoRepository todosrepos;
    @Autowired
    UserRepository userrepos;

    // GET /users - returns all the users
    @GetMapping("/users")
    public List<User> allUsers()
    {
        return userrepos.findAll();
    }

    // GET /todos - return all the todos
    @ApiOperation(value="List all todos", response=List.class)
    @ApiResponses(value={
            @ApiResponse(code = 200, message="Successfully retrieve list"),
            @ApiResponse(code = 401, message="not authorized for this resource"),
            @ApiResponse(code=403, message="access to resource forbidden"),
            @ApiResponse(code=404, message="resource not found")
    })
    @GetMapping("/todos")
    public List<Todo> allTodos()
    {
        return todosrepos.findAll();
    }

    // GET /users/userid/{userid} - return the user based off of the user id
    @GetMapping("/users/userid/{userid}")
    public Optional<User> userById(@PathVariable long id)
    {
       return userrepos.findById(id);
    }

    // GET /users/username/{username} - return the user based off of the user name
    @GetMapping("users/username/{username}")
    public User userByName(@PathVariable String name)
    {
        return userrepos.findByUsername(name);
    }

    // GET /todos/todoid/{todoid} - return the todo based off of the todo id
    public Todo todoByid(@PathVariable long id)
    {
        return todosrepos.findByTodoid(id);
    }

    // GET /todos/users - return a listing of the todos with its assigned user's name
    @ApiOperation(value="List all todos with its assigned user's name", response=List.class)
    @ApiResponses(value={
            @ApiResponse(code = 200, message="Successfully retrieve list"),
            @ApiResponse(code = 401, message="not authorized for this resource"),
            @ApiResponse(code=403, message="access to resource forbidden"),
            @ApiResponse(code=404, message="resource not found")
    })
    @GetMapping("/todos/users")
    public List<Object[]> todosForUsername(@PathVariable String name)
    {
        return todosrepos.todosByusername();
    }

    // POST /users - adds a user
    @PostMapping("/users")
    public User addUser(@RequestBody User newUser) throws URISyntaxException
    {
        return userrepos.save(newUser);
    }

    // POST /todos - adds a todo
    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo newTodo) throws URISyntaxException
    {
        return todosrepos.save(newTodo);
    }

    // PUT /users/userid/{userid} - updates a user based on userid
    @PutMapping("/users/userid/{userid}")
    public User changeUser(@RequestBody User newUser, @PathVariable long id) throws URISyntaxException
    {
        Optional<User> updateUser = userrepos.findById(id);
        if (updateUser.isPresent())
        {
            newUser.setUserid(id);
            userrepos.save(newUser);
            return newUser;
        }
        else
        {
            return null;
        }
    }

    // PUT /todos/todoid/{todoid} - updates a todo based on todoid
    @ApiOperation(value="Update todo by its id", response=List.class)
    @ApiResponses(value={
            @ApiResponse(code = 200, message="Successfully updated"),
            @ApiResponse(code = 401, message="not authorized for this resource"),
            @ApiResponse(code=403, message="access to resource forbidden"),
            @ApiResponse(code=404, message="resource not found")
    })
    @PutMapping("/todos/todoid/{todoid}")
    public Todo changeTodo(@RequestBody Todo newTodo, @PathVariable long id) throws URISyntaxException
    {
        Optional<Todo> updateTodo = todosrepos.findById(id);
        if (updateTodo.isPresent())
        {
            newTodo.setTodoid(id);
            todosrepos.save(newTodo);
            return newTodo;
        }
        else
        {
            return null;
        }
    }

    // DELETE /users/userid/{userid} - Deletes a user based off of their userid and deletes all their associated todos
    @DeleteMapping("/users/userid/{userid}")
    public User deleteUserById(@PathVariable long id)
    {
       return userrepos.deleteByUserid(id);
    }

    // DELETE /todos/todoid/{todoid} - deletes a todo based off its todoid
    @ApiOperation(value="Delete todo by its id", response=List.class)
    @ApiResponses(value={
            @ApiResponse(code = 200, message="Successfully deleted"),
            @ApiResponse(code = 401, message="not authorized for this resource"),
            @ApiResponse(code=403, message="access to resource forbidden"),
            @ApiResponse(code=404, message="resource not found")
    })
    @DeleteMapping("/todos/todoid/{todoid}")
    public Todo deleteTodoById(@PathVariable long id)
    {
        return todosrepos.deleteByTodoid(id);
    }
}
