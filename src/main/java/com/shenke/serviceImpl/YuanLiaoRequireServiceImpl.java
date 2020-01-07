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
        System.out.println("+_+_+_+_+_+_+_+_+_+_+_+__+_+");
        System.out.println("kankan到这了吗");
        System.out.println("+_+_+_+_+_+_+_+_+_+_+_+__+_+");
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
