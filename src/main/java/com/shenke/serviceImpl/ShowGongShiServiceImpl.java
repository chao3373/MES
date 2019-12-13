package com.shenke.serviceImpl;

import com.shenke.entity.ShowGongShi;
import com.shenke.repository.ShowGongShiRepository;
import com.shenke.service.ShowGongShiService;
import org.hibernate.validator.constraints.EAN;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ShowGongShiServiceImpl implements ShowGongShiService {

    @Resource
    private ShowGongShiRepository showGongShiRepository;

    @Override
    public void updateShowGongShi(Integer show) {
        showGongShiRepository.updateShowGongShi(show);
    }

    @Override
    public List<ShowGongShi> select() {
        return showGongShiRepository.findAll();
    }
}
