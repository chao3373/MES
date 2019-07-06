package com.shenke.serviceImpl;

import com.shenke.entity.Drawing;
import com.shenke.repository.DrawingRepository;
import com.shenke.service.DrawingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("drawingService")
public class DrawingServiceImpl implements DrawingService {
    @Resource
    private DrawingRepository drawingRepository;

    @Override
    public List<Drawing> list(Drawing drawing) {
        return drawingRepository.findAll();
    }

    @Override
    public void save(Drawing drawing) {
        drawingRepository.save(drawing);
    }


   @Override
    public void delete(Integer id) {
        drawingRepository.deleteById(id);
    }

    @Override
    public List<Drawing> conboList(String q) {
        return drawingRepository.conboList(q);
    }

    @Override
    public Drawing findDrawingId(String DrawingId) {
        return drawingRepository.findDrawingId(DrawingId);
    }

    @Override
    public Drawing findById(Integer id) {
        //return drawingRepository.findByaId(id);
        return drawingRepository.getOne(id);
    }
}
