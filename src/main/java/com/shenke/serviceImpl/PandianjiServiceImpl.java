package com.shenke.serviceImpl;

import com.shenke.entity.Pandianji;
import com.shenke.repository.PandianjiRepository;
import com.shenke.service.PandianjiService;
import com.shenke.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 盘点机Service实现类
 * 
 * @author shao
 *
 */

@Service("pandianjiService")
public class PandianjiServiceImpl implements PandianjiService  {
	
	@Resource
	private PandianjiRepository pandianjiRepository;

	/**
	 * 根据名称查询盘点机名称
	 * 
	 */
	@Override
	public Pandianji findByPandianjiName(String name) {
		return pandianjiRepository.findByPandianjiName(name);
	}

	/*@Override
	public List<Pandianji> list(Pandianji pandianji, Integer page,Integer rows, Direction asc, String... strings) {
		Page<Pandianji> pagePandianji = pandianjiRepository.findAll(new Specification<Pandianji>() {
			@Override
			public Predicate toPredicate(Root<Pandianji> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (pandianji != null) {
					if (StringUtil.isNotEmpty(pandianji.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + pandianji.getName() + "%"));
					}
				}
				return predicate;
			}
		}, Pageable);
		return pagePandianji.getContent();
	}*/
	
	@Override
	public List<Pandianji> list(Pandianji pandianji, Integer page, Integer pageSize, Direction asc, String ...properties){
		Pageable pageable =new PageRequest(page-1, pageSize);
		Page<Pandianji> pageUser = pandianjiRepository.findAll(new Specification<Pandianji>() {
			public Predicate toPredicate(Root<Pandianji> root, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if(pandianji != null) {
					if(StringUtil.isNotEmpty(pandianji.getName())) {
						predicate.getExpressions().add(cb.like(root.get("method"), "%" + pandianji.getName() +"%"));
					}
				}
				return predicate;
			}

			@Override
			public Predicate toPredicate(Root<Pandianji> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return null;
			}

			
		},pageable);
		return pageUser.getContent();
	}
	
	
	
	
	

	@Override
	public Long getCount(Pandianji pandianji) {
		Long count = pandianjiRepository.count(new Specification<Pandianji>() {
			@Override
			public Predicate toPredicate(Root<Pandianji> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (pandianji != null) {
					if (StringUtil.isNotEmpty(pandianji.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + pandianji.getName() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}
		});
		return count;
	}

	@Override
	public Pandianji findById(Integer pandianjiId) {
		return pandianjiRepository.findById(pandianjiId).get();
	}

	@Override
	public void save(Pandianji pandianji) {
		pandianjiRepository.save(pandianji);
	}

	@Override
	public void delete(Integer id) {
		pandianjiRepository.deleteById(id);
		
	}

	/*@Override
	public List<Pandianji> findByName(String string) {
		// TODO Auto-generated method stub
		return pandianjiRepository.findByName(string);
	}*/

	@Override
	public List<Pandianji> findByPid(String string) {
		// TODO Auto-generated method stub
		return pandianjiRepository.findByPid(string);
	}

}