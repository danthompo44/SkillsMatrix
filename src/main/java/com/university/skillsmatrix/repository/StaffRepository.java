package com.university.skillsmatrix.repository;

import com.university.skillsmatrix.entity.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
    Staff findStaffByUserId(Long id);
}
