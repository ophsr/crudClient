package com.devsuperior.crudClient.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.devsuperior.crudClient.dto.ClientDTO;
import com.devsuperior.crudClient.entities.Client;
import com.devsuperior.crudClient.repositories.ClientRepository;
import com.devsuperior.crudClient.services.exceptions.DataBaseException;
import com.devsuperior.crudClient.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(PageRequest pageRequest) {
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(entity -> new ClientDTO(entity));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> obj = repository.findById(id);

        Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found, ID: " + id));

        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {

        try {
            Client entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException  e) {
            throw new ResourceNotFoundException("Id: " + id + " not found");
        }
    }

	public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id: " + id + " not found");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }
	}

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

}
