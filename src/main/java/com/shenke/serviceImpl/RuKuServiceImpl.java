package com.shenke.serviceImpl;

import com.shenke.entity.RuKu;
import com.shenke.repository.RuKuRepository;
import com.shenke.service.RuKuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("ruKuService")
@Transactional
public class RuKuServiceImpl implements RuKuService {

    @Resource
    private RuKuRepository ruKuRepository;

    @Override
    public RuKu findOneByxiaotuCode(String xiaotuCode) {
        return ruKuRepository.findOneByxiaotuCode(xiaotuCode);
    }

    @Override
    public void save(RuKu ruku) {
        ruKuRepository.save(ruku);
    }

    @Override
    public List<RuKu> list() {
        return ruKuRepository.list();
    }

    @Override
    public void updateState(Integer id) {
        ruKuRepository.updateState(id);
    }

    @Override
    public RuKu findById(Integer id) {
        return ruKuRepository.findById(id).get();
    }

    @Override
    public RuKu findBySaleListId(Integer saleListId) {
        return ruKuRepository.findBySaleListId(saleListId);
    }

    @Override
    public List<RuKu> findByDatuCode(String datuCode) {
        return ruKuRepository.findByDatuCode(datuCode);
    }


}
