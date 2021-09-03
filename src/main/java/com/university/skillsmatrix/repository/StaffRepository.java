package com.university.skillsmatrix.repository;

import com.university.skillsmatrix.entity.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
    Staff findStaffByUserId(Long id);
    List<Staff> findStaffsBySkillsId(Long id);
    List<Staff> findStaffByManagerId(Long id);
}
