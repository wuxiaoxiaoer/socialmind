package com.sicdlib.service;


import com.sicdlib.entity.SpiderInfoEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zcx on 2017/11/10.
 */
@Service
@Transactional
public class SpiderService extends DefaultEntityManager<SpiderInfoEntity> {
}
