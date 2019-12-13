package com.shenke.serviceImpl;

import com.shenke.entity.Wuliao;
import com.shenke.repository.WuliaoRepository;
import com.shenke.service.WuliaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public List<Wuliao> findByBigDrawingId(Integer id) {
        return wuliaoRepository.findByBigDrawingId(id);
    }

    @Override
    public List<Wuliao> findBySaleListId(Integer saleListId) {
        return wuliaoRepository.findBySaleListId(saleListId);
    }

    @Override
    public void saveOld(Wuliao wuliao1) {
        wuliaoRepository.save(wuliao1);
    }

    @Override
    public void deleteByBigDrawingId(Integer id) {
        wuliaoRepository.deleteByBigDrawingId(id);
    }

    @Override
    public void deleteById(Integer id) {
        wuliaoRepository.deleteById(id);
    }


}
