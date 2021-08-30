package com.university.skillsmatrix.service;

import com.university.skillsmatrix.convertor.manager.ManagerToDTOConvertor;
import com.university.skillsmatrix.domain.ManagerDTO;
import com.university.skillsmatrix.entity.Manager;
import com.university.skillsmatrix.exceptions.ResourceNotFoundException;
import com.university.skillsmatrix.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    private final ManagerRepository repo;
    private final ManagerToDTOConvertor convertor;

    public ManagerDTO getManagerByAppUserId(Long id){
        Optional<Manager> manager = Optional.ofNullable(repo.findManagerByUserId(id));
        if(manager.isPresent()){
            System.out.println(manager.get().getDetails().getFirstName());
            return convertor.convert(manager.get());
        }
        else{
            throw new ResourceNotFoundException("Manager is not found", "Enter a valid id");
        }
    }
}
