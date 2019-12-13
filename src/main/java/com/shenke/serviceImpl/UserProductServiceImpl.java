package com.shenke.serviceImpl;

import com.shenke.entity.UserProduct;
import com.shenke.repository.UserProductRespository;
import com.shenke.service.UserProductService;
import com.shenke.util.StringUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserProductServiceImpl implements UserProductService {

    @Resource
    private UserProductRespository userProductRespository;

    @Override
    public void save(UserProduct userProduct) {
        userProductRespository.save(userProduct);
    }

    @Override
    public void delectByUserId(Integer id) {
        userProductRespository.delectByUserId(id);
    }

    @Override
    public List<UserProduct> list(UserProduct userProduct) {

        return userProductRespository.findAll(new Specification<UserProduct>() {
            @Override
            public Predicate toPredicate(Root<UserProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(userProduct != null){
                    if(StringUtil.isNotEmpty(userProduct.getUserName())){
                        predicate.getExpressions().add(cb.equal(root.get("user").get("trueName"), userProduct.getUserName()));
                    }

                    if(userProduct.getProcessId() != null){
                        predicate.getExpressions().add(cb.equal(root.get("process").get("id"),userProduct.getProcessId()));
                    }

                    if(userProduct.getbDate() != null){
                        System.out.println("********************开始时间********************");
                        System.out.println(userProduct.getbDate());
                        System.out.println("********************开始时间********************");
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("dateInProduct"),userProduct.getbDate()));
                    }
                    if(userProduct.geteDate() != null){
                        System.out.println("********************结束时间********************");
                        System.out.println(userProduct.geteDate());
                        System.out.println("********************结束时间********************");
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("dateInProduct"),userProduct.geteDate()));
                    }
                }
                return predicate;

            }
        });
    }

    @Override
    public List<UserProduct> findAll() {
        return userProductRespository.findAll();
    }

    @Override
    public void deleteByProcessIds(Integer[] ids) {
        System.out.println("1");
        userProductRespository.deleteByProcessIds(ids);
    }


}
