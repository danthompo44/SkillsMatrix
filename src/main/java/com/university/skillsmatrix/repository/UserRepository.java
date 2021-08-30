package com.university.skillsmatrix.repository;

import com.university.skillsmatrix.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
    public AppUser getUserByUsername(String username);
}
