package com.onezero.base.dao;

import com.onezero.base.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author hao
 * @create 2019-07-13 ${TIM}
 */
public interface LabelRepository extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label>{
}
