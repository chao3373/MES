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
    public void deleteByDrawingId(Integer drawingId) {
        drawingProcessRepository.deleteByDrawingId(drawingId);
    }

    @Override
    public void save(DrawingProcess drawingProcess) {
        drawingProcessRepository.save(drawingProcess);
    }

    @Override
    public List<DrawingProcess> findByDrawingId(Integer id) {
        return drawingProcessRepository.findByDrawingId(id);
    }

    @Override
    public List<DrawingProcess> findByArr(Integer[] a) {
        return drawingProcessRepository.findByArr(a);
    }
}
