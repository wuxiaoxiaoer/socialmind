package com.sicdlib.service;

import com.sicdlib.entity.ConfigConfigitemEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zcx on 2017/11/15.
 */
@Service
@Transactional
public class ConfigConfigItemService extends DefaultEntityManager<ConfigConfigitemEntity> {
}
