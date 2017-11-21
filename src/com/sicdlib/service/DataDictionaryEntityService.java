package com.sicdlib.service;

import com.sicdlib.entity.DataDictionaryEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DataDictionaryEntityService extends DefaultEntityManager<DataDictionaryEntity> {

}
