package com.sicdlib.service;

import com.sicdlib.entity.LogEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zcx on 2017/11/9.
 */
@Service
@Transactional
public class LogService extends DefaultEntityManager<LogEntity> {




}
