package com.university.skillsmatrix.services;

import com.university.skillsmatrix.convertor.category.DTOToSkillCategoryConvertor;
import com.university.skillsmatrix.convertor.category.SkillCategoryToDTOConvertor;
import com.university.skillsmatrix.domain.SkillCategoryDTO;
import com.university.skillsmatrix.entity.SkillCategory;
import com.university.skillsmatrix.repository.SkillCategoryRepository;
import com.university.skillsmatrix.service.CategoryService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryServiceTest {
    @Mock private SkillCategoryRepository catRepo;
    @Mock private SkillCategoryToDTOConvertor catToDTOConvertor;
    @Mock private DTOToSkillCategoryConvertor dtoToCatConvertor;

    private AutoCloseable closeable;

    @InjectMocks
    private CategoryService catService;

    //Dummy Objects
    private final SkillCategory cat1 = new SkillCategory();
    private final SkillCategory cat2 = new SkillCategory();

    private SkillCategoryDTO dto1;
    private SkillCategoryDTO dto2;

    @BeforeEach
    void init(){
        closeable = openMocks(this);

        //Set Categories
        cat1.setId(39);
        cat1.setDescription("Software");
        cat2.setId(40);
        cat2.setDescription("Networks");

        dto1 = convertCategory(cat1);
        dto2 = convertCategory(cat2);
    }

    private SkillCategoryDTO convertCategory(SkillCategory s){
        SkillCategoryDTO dto = new SkillCategoryDTO();
        dto.setId(s.getId());
        dto.setDescription(s.getDescription());

        return dto;
    }

    @AfterEach
    void close() throws Exception {
        closeable.close();
    }

    @Test
    public void test01WhenGivenARequestForAllCategoriesReturnAll(){
        List<SkillCategory> requestedListOfCategories = new ArrayList<>();
        requestedListOfCategories.add(cat1);
        requestedListOfCategories.add(cat2);
        when(catRepo.findAll()).thenReturn(requestedListOfCategories);

        //Cat Convertor Mock Behaviour
        when(catToDTOConvertor.convert(cat1)).thenReturn(dto1);
        when(catToDTOConvertor.convert(cat2)).thenReturn(dto2);

        //Define List of expected response objects
        List<SkillCategoryDTO> expectedListOfCategories = new ArrayList<>();
        expectedListOfCategories.add(dto1);
        expectedListOfCategories.add(dto2);

        List<SkillCategoryDTO> newCategories = catService.getAllCategories();

        //Assert sizes are the same
        assertEquals(newCategories.size(), requestedListOfCategories.size());

        //Check elements are the same
        for(int i = 0; i < newCategories.size(); i++){
            assertEquals(newCategories.get(i), expectedListOfCategories.get(i));
        }
    }

    @Test
    public void test02WhenGivenARequestForACategoryReturnExpected(){
        when(catRepo.findById(39L)).thenReturn(java.util.Optional.of(cat1));

        //Cat Convertor Mock Behaviour
        when(catToDTOConvertor.convert(cat1)).thenReturn(dto1);

        SkillCategoryDTO expectedCat = dto1;

        SkillCategoryDTO newCat = catService.getCategoryById(39L);

        //Assert Cats are the same
        assertEquals(newCat, expectedCat);
    }

    @Test
    public void test03WhenGivenARequestToSaveACategoryReturnExpectedAndCategoryIsSaved(){
        List<SkillCategory> requestedListOfCategories = new ArrayList<>();
        requestedListOfCategories.add(cat1);
        when(catRepo.findAll()).thenReturn(requestedListOfCategories);

        //Cat Convertor Mock Behaviour
        when(catToDTOConvertor.convert(cat1)).thenReturn(dto1);
        when(catToDTOConvertor.convert(cat2)).thenReturn(dto2);
        when(dtoToCatConvertor.convert(dto2)).thenReturn(cat2);

        //Define List of expected response objects
        List<SkillCategoryDTO> expectedListOfCategories = new ArrayList<>();
        expectedListOfCategories.add(dto1);

        List<SkillCategoryDTO> newCategories = catService.getAllCategories();

        //Assert sizes are the same
        assertEquals(newCategories.size(), requestedListOfCategories.size());

        //Check elements are the same
        for(int i = 0; i < newCategories.size(); i++){
            assertEquals(newCategories.get(i), expectedListOfCategories.get(i));
        }

        //Add category to expected list
        requestedListOfCategories.add(cat2);
        when(catRepo.findAll()).thenReturn(requestedListOfCategories);

        expectedListOfCategories.add(dto2);
        newCategories = catService.getAllCategories();

        //Assert sizes are the same
        assertEquals(newCategories.size(), requestedListOfCategories.size());

        //Check elements are the same
        for(int i = 0; i < newCategories.size(); i++){
            assertEquals(newCategories.get(i), expectedListOfCategories.get(i));
        }
    }

    @Test
    public void test04WhenGivenARequestToDeleteACategoryItIsDeleted(){
        List<SkillCategory> requestedListOfCategories = new ArrayList<>();
        requestedListOfCategories.add(cat1);
        requestedListOfCategories.add(cat2);
        when(catRepo.findAll()).thenReturn(requestedListOfCategories);

        //Cat Convertor Mock Behaviour
        when(catToDTOConvertor.convert(cat1)).thenReturn(dto1);
        when(catToDTOConvertor.convert(cat2)).thenReturn(dto2);

        //Define List of expected response objects
        List<SkillCategoryDTO> expectedListOfCategories = new ArrayList<>();
        expectedListOfCategories.add(dto1);
        expectedListOfCategories.add(dto2);

        List<SkillCategoryDTO> newCategories = catService.getAllCategories();

        //Assert sizes are the same
        assertEquals(newCategories.size(), requestedListOfCategories.size());

        //Check elements are the same
        for(int i = 0; i < newCategories.size(); i++){
            assertEquals(newCategories.get(i), expectedListOfCategories.get(i));
        }

        //Remove objects from mock behaviours
        requestedListOfCategories.remove(cat2);
        when(catRepo.findAll()).thenReturn(requestedListOfCategories);
        expectedListOfCategories.remove(dto2);

        catService.deleteCategoryById(cat2.getId());

        newCategories = catService.getAllCategories();
        //Assert sizes are the same
        assertEquals(newCategories.size(), requestedListOfCategories.size());

        //Check elements are the same
        for(int i = 0; i < newCategories.size(); i++){
            assertEquals(newCategories.get(i), expectedListOfCategories.get(i));
        }

    }
}
