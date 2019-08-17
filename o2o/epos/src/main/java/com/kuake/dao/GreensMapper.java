package com.kuake.dao;

import com.kuake.dao.BaseGreensMapper;
import com.kuake.vo.Vo;

public interface GreensMapper extends BaseGreensMapper {

	boolean deleteByVo(Vo vo) throws Exception;

}
