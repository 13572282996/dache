package com.lczx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lczx.dao.CodeDao;
import com.lczx.entity.Code;
import com.lczx.service.CodeService;

@Service("codeServiceImpl")
public class CodeServiceImpl extends BaseServiceImpl<Code, Long> implements
        CodeService
{
    @Resource(name = "codeDaoImpl")
    private CodeDao codeDao;
    
    @Resource(name = "codeDaoImpl")
    public void setBaseDao(CodeDao codeDao)
    {
        super.setBaseDao(codeDao);
    }

    @Override
    public List<Code> queryCodes(String mobile)
    {
        return codeDao.queryCodes(mobile);
    }
    
}
