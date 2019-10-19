package com.shenke.serviceImpl;

import com.shenke.entity.BigDrawing;
import com.shenke.entity.BigDrawingProcess;
import com.shenke.repository.BigDrawingProcessRepository;
import com.shenke.service.BigDrawingProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("bigDrawingProcessService")
@Transactional
public class BigDrawingProcessServiceImpl implements BigDrawingProcessService {

    @Resource
    private BigDrawingProcessRepository bigDrawingProcessRepository;
    @Override
    public void deleteByDrawingId(Integer drawingId) {
        bigDrawingProcessRepository.deleteByDrawingId(drawingId);
    }

    @Override
    public void save(BigDrawingProcess bigDrawingProcess) {
        bigDrawingProcessRepository.save(bigDrawingProcess);
    }

    @Override
    public List<BigDrawingProcess> findByBigDrawingId(Integer id) {
        return bigDrawingProcessRepository.findByBigDrawingId(id);
    }
}
