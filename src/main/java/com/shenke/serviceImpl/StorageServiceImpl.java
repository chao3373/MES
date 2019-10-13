package com.shenke.serviceImpl;

import com.shenke.entity.Storage;
import com.shenke.repository.StorageRepository;
import com.shenke.service.StorageService;
import com.shenke.util.StringUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("storageService")
@Transactional
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageRepository storageRepository;

    @Override
    public void save(Storage storage) {
        storageRepository.save(storage);
    }


    @Override
    public Storage findById(Integer id) {
        return storageRepository.findById(id).get();
    }

    @Override
    public void updateState(Integer[] ids, String state) {
        storageRepository.updateState(ids,state);
    }

    @Override
    public List<Storage> findByState(String state) {
        return storageRepository.findByState(state);
    }

    @Override
    public List<Storage> detail(Map<String, Object> map) {
        return storageRepository.findAll(new Specification<Storage>() {
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtil.isNotEmpty((String)map.get("date"))) {
                    String date = (String) map.get("date");
                    try {
                        String st = date + " 00:00:00";
                        String ed = date + " 23:59:59";
                        System.out.println(st);
                        System.out.println(ed);
                        Date star = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(st);
                        Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ed);
                        System.out.println(star);
                        System.out.println(end);
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("chuKuDate"), star));
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("chuKuDate"), end));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                predicate.getExpressions().add(cb.equal(root.get("state"), "出库"));

                return predicate;
            }
        });//, new Sort(Sort.Direction.ASC, "peasant", "name", "model", "price", "length", "color", "realityweight"));
    }
}
