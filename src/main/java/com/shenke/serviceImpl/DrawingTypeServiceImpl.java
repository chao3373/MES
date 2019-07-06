package com.shenke.serviceImpl;

import com.shenke.entity.DrawingType;
import com.shenke.repository.DrawingTypeRespository;
import com.shenke.service.DrawingTypeService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service("drawingTypeService")
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
}
