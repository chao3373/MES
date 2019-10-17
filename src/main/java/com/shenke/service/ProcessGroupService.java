package com.shenke.service;

import com.shenke.entity.ProcessGroup;

import java.util.List;

public interface ProcessGroupService {

    List<ProcessGroup> findAll();

    void add(ProcessGroup processGroup);

    void deleteById(Integer id);

    List<ProcessGroup> findBytext(String s);

    ProcessGroup findById(Integer id);
}
