package com.shenke.serviceImpl;

import com.shenke.entity.Log;
import com.shenke.repository.LogRepository;
import com.shenke.repository.UserRepository;
import com.shenke.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 15:42
 * @Description:
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private LogRepository logRepository;

    @Override
    public void save(Log log) {
        log.setTime(new Date());//设置操作时间
        log.setUser(userRepository.findByUserName((String) SecurityUtils.getSubject().getPrincipal()));//设置当前操作用户
        logRepository.save(log);
    }
}
