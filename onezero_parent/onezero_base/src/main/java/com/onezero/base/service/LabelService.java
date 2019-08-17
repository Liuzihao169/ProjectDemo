package com.onezero.base.service;

import com.onezero.base.dao.LabelRepository;
import com.onezero.base.entity.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author hao
 * @create 2019-07-13 ${TIM}
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private  LabelRepository repository;
    @Autowired
    private IdWorker idWorker;
    public List<Label> findAll(){
        return repository.findAll();
    }

    public Label findById(String id){
        return repository.findById(id).get();
    }

    public void save(Label label){
        //创建id
        label.setId(idWorker.nextId()+"");
        repository.save(label);
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

    // 更新其实也就是重新保存一次
    public void update(Label label){
        repository.save(label);
    }

    //条件搜索
    public List<Label> findSearch(Label label){
        return  repository.findAll(creatSpecification(label));
    }

    public PageResult<Label> pageQuery(Label label,int page,int size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Label> pageList = repository.findAll(creatSpecification(label), pageable);

        //构建PageResult
        PageResult<Label> pageResult = new PageResult<>();
        pageResult.setTotal(pageList.getTotalElements());
        pageResult.setRows(pageList.getContent());

        return pageResult;
    }


    /**
     * 动态条件构建
     * @param label
     * @return
     */
    private Specification creatSpecification(Label label){
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (null != label.getLabelname() && label.getLabelname().trim().length() > 0) {
                    //通过根 获得要限制的分支
                    Path path = root.get("labelname");
                    // where labename like '% labelname %'
                    Predicate predicate = criteriaBuilder.like(path, "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }
                if (null != label.getState() && label.getState().trim().length() > 0) {
                    Path path = root.get("state");
                    Predicate predicate = criteriaBuilder.equal(path, label.getState());
                    list.add(predicate);
                }
                //没有查询条件的时候，返回null 表示查询所有
                if (list.size() == 0) return null;

                Predicate predicatearr[] = new Predicate[list.size()];
                //底层是System.arraycopy
                list.toArray(predicatearr);
                return criteriaBuilder.and(predicatearr); //多个条件组合起来
            }

        };
    }

}
