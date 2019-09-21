package com.shenke.serviceImpl;

import com.shenke.entity.UserProcess;
import com.shenke.repository.UserProcessRepository;
import com.shenke.service.UserProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("/userProcessService")
public class UserProcessServiceImpl implements UserProcessService {

    @Resource
    private UserProcessRepository userProcessRepository;

    @Override
    public void deleteByUserId(Integer id) {
        userProcessRepository.deleteByUserId(id);
    }

    @Override
    public void save(UserProcess userProcess) {
        userProcessRepository.save(userProcess);
    }

    @Override
    public List<UserProcess> findByUserId(Integer id) {
        return userProcessRepository.findByUserId(id);
    }
}
