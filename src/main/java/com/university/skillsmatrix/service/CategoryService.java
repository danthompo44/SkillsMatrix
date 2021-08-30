package com.university.skillsmatrix.service;

import com.university.skillsmatrix.convertor.category.SkillCategoryToDTOConvertor;
import com.university.skillsmatrix.domain.SkillCategoryDTO;
import com.university.skillsmatrix.entity.SkillCategory;
import com.university.skillsmatrix.repository.SkillCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final SkillCategoryRepository categoryRepository;
    private final SkillCategoryToDTOConvertor catConvertor;

    @Transactional
    public List<SkillCategoryDTO> getAllCategories(){
        Iterable<SkillCategory> categoryIterable = categoryRepository.findAll();
        List<SkillCategoryDTO> skillCategoryDTOList = new ArrayList<>();

        for(SkillCategory category: categoryIterable){
            SkillCategoryDTO dto = catConvertor.convert(category);
            skillCategoryDTOList.add(dto);
        }

        return skillCategoryDTOList;
    }
}
