package com.onezero.spit.dao;

import com.onezero.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author hao
 * @create 2019-07-18 ${TIM}
 */
public interface SpitDao extends MongoRepository<Spit,String> {

    /**
     *  根据父id 查询子id
     * @param parentid
     * @return
     */
    Page<Spit> findByParentid(String parentid, Pageable pageable);
}
