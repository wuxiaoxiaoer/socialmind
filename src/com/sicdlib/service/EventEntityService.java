package com.sicdlib.service;

import com.sicdlib.entity.EventEntity;
import com.sicdlib.entity.ObjectEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventEntityService extends DefaultEntityManager<EventEntity> {


}
