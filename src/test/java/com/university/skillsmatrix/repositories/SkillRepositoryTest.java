package com.university.skillsmatrix.repositories;

import com.university.skillsmatrix.entity.Skill;
import com.university.skillsmatrix.entity.SkillCategory;
import com.university.skillsmatrix.repository.SkillRepository;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import java.util.stream.StreamSupport;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SkillRepositoryTest {
    @Autowired
    private SkillRepository repo;

    private Skill skill = new Skill();
    private SkillCategory category = new SkillCategory();

    void init(){
        //Set category as database
        category.setId(1);
        category.setDescription("Software");

        skill.setId(1);
        skill.setName("Java");
        skill.setCategory(category);
    }

    @Test
    public void test_01WhenGivenARequestForAllSkillsAllAreReturned(){
        Iterable<Skill> skills = repo.findAll();
        //Assert size is 5
        assertSkillListSize(5, skills);
    }

    @Test
    public void test_02WhenGivenARequestForASkillByIdReturnExpected(){
        init();
        assertSkill(skill, repo.findById(skill.getId()).get());
    }

    @Test
    public void test_03WhenGivenARequestToFindSkillsByCategoryIdCorrectAmountAreReturned(){
        Iterable<Skill> skills = repo.findSkillsByCategoryId(1);
        assertSkillListSize(2, skills);
    }

    @Test
    public void test_04WhenGivenARequestToUpdateASkillItIsUpdate(){
        init();
        updateSkill();
        assertSkill(skill, repo.save(skill));
    }

    @Test
    public void test_05WhenGivenARequestToDeleteAValidSkillItIsDeleted(){
        repo.delete(skill);
        assertEquals(repo.findById(skill.getId()), Optional.empty());
    }

    private void assertSkill(Skill expected, Skill returned){
        assertEquals(expected.getId(), returned.getId());
        assertEquals(expected.getName(), returned.getName());

        //Assert Category
        assertEquals(expected.getCategory().getId(), returned.getCategory().getId());
        assertEquals(expected.getCategory().getDescription(), returned.getCategory().getDescription());
    }

    private void assertSkillListSize(int expectedSize, Iterable<Skill> skills){
        long size = StreamSupport.stream(skills.spliterator(), false).count();
        assertEquals(expectedSize, size);
    }

    private void updateSkill(){
        skill.setName("Updated Skill");
    }
}
