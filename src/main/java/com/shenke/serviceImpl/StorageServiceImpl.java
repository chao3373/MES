package com.shenke.serviceImpl;

import com.shenke.entity.Storage;
import com.shenke.repository.StorageRepository;
import com.shenke.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("storageService")
@Transactional
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageRepository storageRepository;

    @Override
    public void save(Storage storage) {
        storageRepository.save(storage);
    }

    @Override
    public Storage selectByMaxId() {
        return storageRepository.selectByMaxId();
    }

    @Override
    public Storage findById(Integer id) {
        return storageRepository.findById(id).get();
    }
}
