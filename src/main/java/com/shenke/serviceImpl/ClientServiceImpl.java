package com.shenke.serviceImpl;


import com.shenke.entity.Client;
import com.shenke.repository.ClientRepository;
import com.shenke.service.ClientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("clientService")
public class ClientServiceImpl  implements ClientService {

    @Resource
    private ClientRepository clientRepository;

    @Override
    public List<Client> list(Client client) {
        return clientRepository.findAll();
    }

    @Override
    public Client findByClientName(String name) {
        return clientRepository.findByClientName(name);
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client findById(Integer id) {
        return clientRepository.getOne(id);
    }

    @Override
    public void deleteById(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> findCombobox(String string) {
        return clientRepository.findCombobox(string);
    }
}
