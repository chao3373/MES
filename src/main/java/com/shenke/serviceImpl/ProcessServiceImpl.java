package com.shenke.serviceImpl;

import com.shenke.entity.DrawingProcess;
import com.shenke.entity.Process;
import com.shenke.repository.DrawingProcessRepository;
import com.shenke.repository.ProcessRepository;
import com.shenke.service.ProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("processService")
public class ProcessServiceImpl implements ProcessService {

    @Resource
    private ProcessRepository processRepository;

    @Resource
    private DrawingProcessRepository drawingProcessRepository;

    @Override
    public List<Process> list(Process process) {
        return processRepository.findAll();
    }

    @Override
    public Process findById(Integer id) {
        return processRepository.findById(id).get();
    }


    @Override
    public List<Process> findProcessCombobox(String string) {
        return processRepository.findProcessCombobox(string);
    }

    @Override
    public void save(Process process) {
        processRepository.save(process);
    }


    @Override
    public List<Process> findByPGId(Integer id) {
        return processRepository.findByPGId(id);
    }

    @Override
    public void deleteByPGId(Integer id) {
        processRepository.deleteByPGId(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for(int i = 0;i<ids.length;i++){
            processRepository.deleteById(ids[i]);
        }
    }

    @Override
    public List<Process> selectByName(String s) {
        return processRepository.selectByName(s);
    }
}
