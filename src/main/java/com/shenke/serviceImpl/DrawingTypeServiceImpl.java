package com.shenke.serviceImpl;

import com.shenke.entity.DrawingType;
import com.shenke.repository.DrawingTypeRespository;
import com.shenke.service.DrawingTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("drawingTypeService")
@Transactional
public class DrawingTypeServiceImpl implements DrawingTypeService {

    @Resource
    private DrawingTypeRespository drawingTypeRespository;

    @Override
    public void addSonDrawing(DrawingType drawingType) {
        drawingTypeRespository.save(drawingType);
    }

    @Override
    public List<DrawingType> findByBigDrawingId(Integer id) {
        return drawingTypeRespository.findByBigDrawingId(id);
    }

    @Override
    public void deleteByBigDrawingId(Integer id) {
        drawingTypeRespository.deleteByBigDrawingId(id);
    }

    @Override
    public void deleteById(Integer id) {
        drawingTypeRespository.deleteById(id);
    }

    @Override
    public DrawingType findById(Integer id) {
        return drawingTypeRespository.findById(id).get();
    }

    @Override
    public void save(DrawingType drawingType) {
        drawingTypeRespository.save(drawingType);
    }


}
