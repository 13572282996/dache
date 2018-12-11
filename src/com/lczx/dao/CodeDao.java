package com.lczx.dao;

import java.util.List;

import com.lczx.entity.Code;

public interface CodeDao extends BaseDao<Code, Long>
{
    List<Code> queryCodes(String mobile);
}
