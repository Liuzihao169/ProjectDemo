package com.onezero.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.onezero.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 通过id查询并且分页 与Specification中封装查询对象效果一样
     * where id =?1
     * 建议使用原生sql语句
     * @param id
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM  tb_pl pl  LEFT  JOIN tb_problem p ON p.id=pl.problemid WHERE pl.labelid = ? ORDER BY p.createtime DESC ",nativeQuery = true)
    public Page<Problem>findNewListByLabelId(String id, Pageable pageable);

    /**
     * 最热的回复的问题
     * @param id
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM tb_pl pl LEFT JOIN tb_problem p ON " +
            "p.id = pl.problemid WHERE pl.labelid = ? ORDER BY p.reply DESC",nativeQuery = true)
    public Page<Problem>findHotListByLabelId(String id, Pageable pageable);

    /**
     * 未回复的问题
     * @param id
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM tb_pl pl LEFT JOIN tb_problem p ON " +
            "p.id = pl.problemid WHERE pl.labelid = ? AND p.reply=0 ORDER BY p.createtime",nativeQuery = true)
    public Page<Problem>findWaitListByLabelId(String id, Pageable pageable);
}
