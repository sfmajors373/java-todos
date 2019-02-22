package com.lambda.todo.models;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;
    @Column(nullable = false)
    private String username;

    public User()
    {
    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
