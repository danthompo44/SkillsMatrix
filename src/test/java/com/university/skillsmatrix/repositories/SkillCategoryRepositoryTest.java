package com.university.skillsmatrix.repositories;

import com.university.skillsmatrix.entity.SkillCategory;
import com.university.skillsmatrix.repository.SkillCategoryRepository;
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
public class SkillCategoryRepositoryTest {
    @Autowired
    private SkillCategoryRepository repo;

    private SkillCategory category = new SkillCategory();

    void init(){
        //Set category as database
        category.setId(1);
        category.setDescription("Software");
    }

    @Test
    public void test_01WhenGivenARequestForAllCategoriesSizeIsAsExpected(){
        Iterable<SkillCategory> categories = repo.findAll();

        //Should be 5 categories
        long size = StreamSupport.stream(categories.spliterator(), false).count();
        assertEquals(5, size);
    }

    @Test
    public void test_02WhenGivenARequestToGetACategoryByIdReturnCategory(){
        init();
        assertCategory(category, repo.findById(1L).get());
    }

    @Test
    public void test_03WhenGivenARequestToUpdateACategoryItIsUpdated(){
        init();
        updateCategory();
        assertEquals(category, repo.save(category));
    }

    @Test
    public void test_04WhenGivenARequestToDeleteACategoryItIsDeleted(){
        repo.delete(category);
        assertEquals(repo.findById(category.getId()), Optional.empty());
    }

    private void assertCategory(SkillCategory expected, SkillCategory returned){
        assertEquals(expected.getId(), returned.getId());
        assertEquals(expected.getDescription(), returned.getDescription());
    }

    private void updateCategory(){
        category.setDescription("Updated Description");
    }
}
