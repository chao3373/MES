package com.shenke.entity;

import javax.persistence.*;

/**
 * 工序实体类
 */

@Entity
@Table(name = "t_process")
public class Process {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;//工序名称

    @ManyToOne
    @JoinColumn
    private ProcessGroup processGroup;

    public ProcessGroup getProcessGroup() {
        return processGroup;
    }

    public void setProcessGroup(ProcessGroup processGroup) {
        this.processGroup = processGroup;
    }

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

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", processGroup=" + processGroup +
                '}';
    }
}
