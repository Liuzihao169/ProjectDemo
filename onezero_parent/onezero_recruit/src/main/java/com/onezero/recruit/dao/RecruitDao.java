package com.onezero.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.onezero.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口 招聘
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

	/**
	 *
	 * @param state
	 * @return
	 */
	public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);

	public List<Recruit> findTop12ByStateOrderByCreatetimeDesc(String state);

}
