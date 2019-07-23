package com.shenke.serviceImpl;

import com.shenke.entity.TemporaryStorage;
import com.shenke.repository.TemporaryStorageRepository;
import com.shenke.service.TemporaryStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("temporaryStorageService")
@Transactional
public class TemporaryStorageServiceImpl implements TemporaryStorageService {

    @Resource
    private TemporaryStorageRepository temporaryStorageRepository;

    @Override
    public void save(TemporaryStorage temporaryStorage) {
        temporaryStorageRepository.save(temporaryStorage);
    }
}
