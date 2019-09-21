package com.shenke.serviceImpl;

import com.shenke.entity.Wuliao;
import com.shenke.repository.WuliaoRepository;
import com.shenke.service.WuliaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("wuliaoService")
@Transactional
public class WuliaoServiceImpl implements WuliaoService {

    @Resource
    private WuliaoRepository wuliaoRepository;

    @Override
    public void save(Wuliao wuliao) {
        wuliaoRepository.save(wuliao);
    }

    @Override
    public Wuliao findByBigDrawingId(Integer id) {
        return wuliaoRepository.findByBigDrawingId(id);
    }

}
