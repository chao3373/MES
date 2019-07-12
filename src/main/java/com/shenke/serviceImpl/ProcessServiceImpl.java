package com.shenke.serviceImpl;

import com.shenke.entity.DrawingProcess;
import com.shenke.entity.Process;
import com.shenke.repository.DrawingProcessRepository;
import com.shenke.repository.ProcessRepository;
import com.shenke.service.ProcessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        return processRepository.getOne(id);
    }


    @Override
    public List<Process> findProcessCombobox(String string) {
        return processRepository.findProcessCombobox(string);
    }
}
