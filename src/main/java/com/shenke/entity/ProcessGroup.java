package com.shenke.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_process_group")
@Entity
public class ProcessGroup {

    @Id
    @GeneratedValue
    private Integer id;

    private String processGroup; //工序组

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcessGroup() {
        return processGroup;
    }

    public void setProcessGroup(String processGroup) {
        this.processGroup = processGroup;
    }

    @Override
    public String toString() {
        return "ProcessGroup{" +
                "id=" + id +
                ", processGroup='" + processGroup + '\'' +
                '}';
    }
}
