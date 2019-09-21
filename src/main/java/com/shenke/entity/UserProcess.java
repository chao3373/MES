package com.shenke.entity;

import javax.persistence.*;

/**
 * 用户、工序实体类
 */
@Entity
@Table(name = "t_user_process")
public class UserProcess {

    @Id
    @GeneratedValue
    private Integer id;//id

    @JoinColumn
    @ManyToOne
    private User user; //用户对象

    @JoinColumn
    @ManyToOne
    private Process process;//工序对象

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "UserProcess{" +
                "id=" + id +
                ", user=" + user +
                ", process=" + process +
                '}';
    }
}
