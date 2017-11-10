package com.sicdlib.service;

import com.sicdlib.entity.CompanyUserEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyUserEntityService extends DefaultEntityManager<CompanyUserEntity> {

}
