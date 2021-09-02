package com.university.skillsmatrix.repository;

import com.university.skillsmatrix.entity.Skill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Long> {
    List<Skill> findSkillsByCategoryId(long category_id);
}
