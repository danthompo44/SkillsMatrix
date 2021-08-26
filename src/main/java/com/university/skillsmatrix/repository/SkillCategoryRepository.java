package com.university.skillsmatrix.repository;

import com.university.skillsmatrix.entity.SkillCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillCategoryRepository extends CrudRepository<SkillCategory, Long> {
}
