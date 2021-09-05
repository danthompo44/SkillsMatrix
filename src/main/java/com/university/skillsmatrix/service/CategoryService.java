package com.university.skillsmatrix.service;

import com.university.skillsmatrix.convertor.category.DTOToSkillCategoryConvertor;
import com.university.skillsmatrix.convertor.category.SkillCategoryToDTOConvertor;
import com.university.skillsmatrix.domain.SkillCategoryDTO;
import com.university.skillsmatrix.entity.SkillCategory;
import com.university.skillsmatrix.exceptions.ResourceNotFoundException;
import com.university.skillsmatrix.repository.SkillCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final SkillCategoryRepository categoryRepository;
    private final SkillCategoryToDTOConvertor catToDTOConvertor;
    private final DTOToSkillCategoryConvertor dtoToCatConvertor;

    @Transactional
    public List<SkillCategoryDTO> getAllCategories(){
        Iterable<SkillCategory> categoryIterable = categoryRepository.findAll();
        List<SkillCategoryDTO> skillCategoryDTOList = new ArrayList<>();

        for(SkillCategory category: categoryIterable){
            SkillCategoryDTO dto = catToDTOConvertor.convert(category);
            skillCategoryDTOList.add(dto);
        }

        return skillCategoryDTOList;
    }

    public SkillCategoryDTO getCategoryById(Long id){
        Optional<SkillCategory> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return catToDTOConvertor.convert(category.get());
        }
        else {
            throw new ResourceNotFoundException("Category id not found", "Enter a valid category id");
        }
    }

    public SkillCategoryDTO save(SkillCategoryDTO dto){
        SkillCategory category = dtoToCatConvertor.convert(dto);
        category = categoryRepository.save(category);
        return catToDTOConvertor.convert(category);
    }

    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
    }
}
