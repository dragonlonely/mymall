package com.dragon.xiaomi.log.service.impl;

import com.dragon.xiaomi.log.pojo.TbLog;
import com.dragon.xiaomi.log.service.LogService;
import com.dragon.xiaomi.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @Date 2019/1/7 19:46
 * @Classname LogServiceImpl
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void addLog(TbLog tbLog) {
        logMapper.addLog(tbLog);
    }
}
