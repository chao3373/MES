package com.shenke.serviceImpl;

import com.shenke.entity.YuanLiaoRequire;
import com.shenke.repository.YuanLiaoRequireRepository;
import com.shenke.service.YuanLiaoRequireService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("yuanLiaoRequireService")
@Transactional
public class YuanLiaoRequireServiceImpl implements YuanLiaoRequireService {

    @Resource
    private YuanLiaoRequireRepository yuanLiaoRequireRepository;

    @Override
    public void save(YuanLiaoRequire yuanLiaoRequire) {
        yuanLiaoRequireRepository.save(yuanLiaoRequire);
    }

    @Override
    public List<YuanLiaoRequire> findBySaleListId(Integer id) {
        return yuanLiaoRequireRepository.findBySaleListId(id);
    }

    @Override
    public void deleteBySaleListId(Integer id) {
        yuanLiaoRequireRepository.deleteBySaleListId(id);
    }

    @Override
    public void deleByWuliaoId(Integer id) {
        yuanLiaoRequireRepository.deleteByWuliaoId(id);
    }
}
