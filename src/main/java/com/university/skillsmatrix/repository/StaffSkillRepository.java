package com.university.skillsmatrix.repository;

import com.university.skillsmatrix.entity.StaffSkill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffSkillRepository extends CrudRepository<StaffSkill, Long> {
    List<StaffSkill> findAllByStaffId(Long id);
}
