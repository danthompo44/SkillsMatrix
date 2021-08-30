package com.university.skillsmatrix.repository;

import com.university.skillsmatrix.entity.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Long> {
    Manager findManagerByUserId(long id);
}
