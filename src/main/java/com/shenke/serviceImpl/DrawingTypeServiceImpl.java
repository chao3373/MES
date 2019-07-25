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
    public List<DrawingType> findBySaleListId(Integer id) {
        return drawingTypeRespository.findBySaleListId(id);
    }

    @Override
    public void setState(Integer id, String state) {
        System.out.println("走到这里啦哈哈哈哈");
        System.out.println(id + state);
        drawingTypeRespository.setState(id,state);
    }


}
