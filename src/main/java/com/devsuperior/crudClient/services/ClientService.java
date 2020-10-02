package com.devsuperior.crudClient.services;

import com.devsuperior.crudClient.dto.ClientDTO;
import com.devsuperior.crudClient.entities.Client;
import com.devsuperior.crudClient.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(PageRequest pageRequest){
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(entity -> new ClientDTO(entity));
    }
}
