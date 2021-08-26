package com.university.skillsmatrix.repository;

import com.university.skillsmatrix.entity.PersonalDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDetailsRepository extends CrudRepository<PersonalDetails, Long> {
}
