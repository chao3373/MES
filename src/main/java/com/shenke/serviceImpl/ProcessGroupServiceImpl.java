package com.shenke.serviceImpl;

import com.shenke.entity.ProcessGroup;
import com.shenke.repository.ProcessGroupRepository;
import com.shenke.service.ProcessGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("processGroupService")
@Transactional
public class ProcessGroupServiceImpl implements ProcessGroupService {

    @Resource
    private ProcessGroupRepository processGroupRepository;

    /***
     * 查询所有部门信息
     * @return
     */
    @Override
    public List<ProcessGroup> findAll() {
        return processGroupRepository.findAll();
    }

    /***
     * 添加部门
     */
    @Override
    public void add(ProcessGroup processGroup) {
        processGroupRepository.save(processGroup);
    }

    /***
     * 根据id删除员工信息
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        processGroupRepository.deleteById(id);
    }

    /***
     * 根据部门名称查询部门信息
     * @param s
     * @return
     */
    @Override
    public List<ProcessGroup> findBytext(String s) {
        return processGroupRepository.findBytext(s);
    }

    @Override
    public ProcessGroup findById(Integer id) {
        return processGroupRepository.findById(id).get();
    }

}
