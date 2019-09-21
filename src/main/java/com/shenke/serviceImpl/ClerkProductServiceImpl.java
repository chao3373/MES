package com.shenke.serviceImpl;

import com.shenke.entity.ClerkProduct;
import com.shenke.repository.ClerkProductRepository;
import com.shenke.service.ClerkProductService;
import com.shenke.util.StringUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("clerkProductService")
public class ClerkProductServiceImpl implements ClerkProductService {

    @Resource
    private ClerkProductRepository clerkProductRepository;

    @Override
    public void save(ClerkProduct clerkProduct) {
        clerkProductRepository.save(clerkProduct);
    }

    @Override
    public List<ClerkProduct> findAll() {
        return clerkProductRepository.findAll();
    }

    @Override
    public List<ClerkProduct> findShengchan(ClerkProduct clerkProduct,String dateInProducedd) {
        return clerkProductRepository.findAll(new Specification<ClerkProduct>() {
            @Override
            public Predicate toPredicate(Root<ClerkProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

               /* if((StringUtil.isNotEmpty(clerkProduct.getDrawingProcess().getInformNum()))){
                    predicate.getExpressions().add(cb.equal(root.get("drawingProcess").get("informNum"),clerkProduct.getDrawingProcess().getInformNum()));
                }*/
                if((clerkProduct.getDrawingProcess().getProcess()!= null)){
                    predicate.getExpressions().add(cb.equal(root.get("drawingProcess").get("process").get("id"),clerkProduct.getDrawingProcess().getProcess().getId()));
                }
                if((clerkProduct.getClerk()!=null)){
                    predicate.getExpressions().add(cb.equal(root.get("clerk").get("id"),clerkProduct.getClerk().getId()));
                }
                if(StringUtil.isNotEmpty(dateInProducedd)){
                    try {
                        java.util.Date star = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateInProducedd + " 00:00:00");
                        java.util.Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateInProducedd + " 23:59:59");
                        System.out.println("开始时间："+star);
                        System.out.println("结束时间："+end);
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("dateInProduct"), star));
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("dateInProduct"), end));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return predicate;
            }
        });
    }
}
