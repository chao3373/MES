package com.shenke.serviceImpl;

import com.shenke.entity.DrawingProcess;
import com.shenke.repository.DrawingProcessRepository;
import com.shenke.service.DrawingProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service("/drawingProcessService")
@Transactional
public class DrawingProcessServiceImpl implements DrawingProcessService {

    @Resource
    private DrawingProcessRepository drawingProcessRepository;

    @Override
    public List<DrawingProcess> findStateNull() {
        return drawingProcessRepository.findStateNull();
    }

    @Override
    public void setState(Integer id) {
        drawingProcessRepository.setState(id);
    }

    @Override
    public List<DrawingProcess> findAuditPass() {
        return drawingProcessRepository.findAuditPass();
    }

    @Override
    public void saveDrawingProcess(DrawingProcess drawingProcess) {
        drawingProcessRepository.save(drawingProcess);
    }

    @Override
    public List<DrawingProcess> findProcessIssue() {
        return drawingProcessRepository.findProcessIssue();
    }

    @Override
    public List<DrawingProcess> findByProcess(Integer id) {
        return drawingProcessRepository.findByProcess(id);
    }

    @Override
    public void updateAccomplishNum(Integer accomplishNum,Integer id) {

        System.out.println("完成数量"+accomplishNum);
        System.out.println("id"+id);
        DrawingProcess drawingProcess = this.findById(id);
        System.out.println("*************对象*************");
        System.out.println(drawingProcess);
        System.out.println("*************对象*************");
        //比较的是 传进来的完成数量与数据库中的相加是否等于num； 若等于 状态改为 生产完成； 若不等于 状态不变

        if((drawingProcess.getAccomplishNum()+accomplishNum) == drawingProcess.getNum()){
            drawingProcessRepository.updateAccomplishNum((drawingProcess.getAccomplishNum()+accomplishNum),id);
            drawingProcessRepository.setState(id);
        }
        else {
            drawingProcessRepository.updateAccomplishNum((drawingProcess.getAccomplishNum()+accomplishNum),id);
        }
    }

    @Override
    public DrawingProcess findById(Integer id) {
        return drawingProcessRepository.findById(id).get();
    }
}
