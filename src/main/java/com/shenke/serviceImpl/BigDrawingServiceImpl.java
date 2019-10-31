package com.shenke.serviceImpl;

import com.shenke.entity.BigDrawing;
import com.shenke.repository.BigDrawingRepository;
import com.shenke.service.BigDrawingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("bigDrawingService")
@Transactional
public class BigDrawingServiceImpl implements BigDrawingService {
    @Resource
    private BigDrawingRepository bigDrawingRepository;

    @Override
    public List<BigDrawing> list(BigDrawing bigDrawing) {
        return bigDrawingRepository.findAll();
    }

    @Override
    public void save(BigDrawing bigDrawing) {
        bigDrawingRepository.save(bigDrawing);
    }


   @Override
    public void delete(Integer id) {
        bigDrawingRepository.deleteById(id);
    }

    @Override
    public List<BigDrawing> conboList(String q) {
        return bigDrawingRepository.conboList(q);
    }


    @Override
    public BigDrawing findBigDrawingId(String DrawingId) {
        return bigDrawingRepository.findDrawingId(DrawingId);
    }

    @Override
    public BigDrawing findById(Integer id) {
        //return bigDrawingRepository.findByaId(id);
        return bigDrawingRepository.getOne(id);
    }

    @Override
    public BigDrawing findByWuLiaoId(String wuliaoId) {
        return bigDrawingRepository.findByWuLiaoId(wuliaoId);
    }

    @Override
    public Integer findIdByDrawingId(String drawingId) {
        return bigDrawingRepository.findIdByDrawingId(drawingId);
    }

    @Override
    public List<BigDrawing> findLikeWuliaoId(String s) {
        return bigDrawingRepository.findLikeWuliaoId(s);
    }

    @Override
    public void updateTime(Double time,Integer id) {
        bigDrawingRepository.updateTime(time,id);
    }


}
