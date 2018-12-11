package com.lczx.service;

import java.util.List;

import com.lczx.entity.Code;

public interface CodeService extends BaseService<Code, Long>
{
    List<Code> queryCodes(String mobile);
}
