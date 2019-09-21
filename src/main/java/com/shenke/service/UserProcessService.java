package com.shenke.service;

import com.shenke.entity.UserProcess;

import java.util.List;

public interface UserProcessService {
    public void deleteByUserId(Integer id);

    public void save(UserProcess userProcess);

    public List<UserProcess> findByUserId(Integer id);
}
