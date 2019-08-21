package com.shenke.serviceImpl;

import com.shenke.entity.Drawing;
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
    public void setState(Integer id) {
        drawingProcessRepository.setState(id);
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

        DrawingProcess drawingProcess = this.findById(id);
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

    @Override
    public Integer findMinProcess(String informNum) {
        return drawingProcessRepository.findMinProcess(informNum);
    }

    @Override
    public String getTodayMaxinformNumNumber() {
        return drawingProcessRepository.getTodayMaxPurchaseNumber();
    }

    @Override
    public List<DrawingProcess> findByInformNum(String informNum) {
        return drawingProcessRepository.findByInformNum(informNum);
    }

    @Override
    public List<DrawingProcess> findBySaleListId(Integer saleListId) {
        return drawingProcessRepository.findBySaleListId(saleListId);
    }

    @Override
    public void updateStateByInformNum(String informNum) {
        drawingProcessRepository.updateStateByInformNum(informNum);
    }

    @Override
    public List<DrawingProcess> findAll() {
        return drawingProcessRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        drawingProcessRepository.deleteById(id);
    }


}
