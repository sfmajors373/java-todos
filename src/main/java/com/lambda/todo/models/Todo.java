package com.lambda.todo.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="todo")
public class Todo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoid;

    @Column(nullable = false)
    private String descripton;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "userid")
    private Set<User> userid;

    public Todo()
    {
    }

    public long getTodoid()
    {
        return todoid;
    }

    public void setTodoid(long todoid)
    {
        this.todoid = todoid;
    }

    public String getDescripton()
    {
        return descripton;
    }

    public void setDescripton(String descripton)
    {
        this.descripton = descripton;
    }

    public Set<User> getUserid()
    {
        return userid;
    }

    public void setUserid(Set<User> userid)
    {
        this.userid = userid;
    }
}
