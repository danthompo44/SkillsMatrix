package com.university.skillsmatrix.web;

import com.university.skillsmatrix.domain.SkillCategoryDTO;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.exceptions.ResourceNotFoundException;
import com.university.skillsmatrix.service.CategoryService;
import com.university.skillsmatrix.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/category")//localhost:8081/category
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final SkillService skillService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public String getAllCategories(Model model){
        model.addAttribute("categoryList", categoryService.getAllCategories());
        model.addAttribute("categoryDTO", new SkillCategoryDTO());
        return "viewAllCategories";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/editPage/{id}")
    public String editPage(@PathVariable long id, //Path variable
                           Model model) {
        try {
            model.addAttribute("category", categoryService.getCategoryById(id));
        } catch (ResourceNotFoundException ex) {
            return "error";
        }

        return "viewACategory";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/update/{id}")
    public String updateCategory(@Valid SkillCategoryDTO dto,
                                 BindingResult result,
                                 Model model) {
        if(result.hasErrors()){
            model.addAttribute("category", dto);
            return "viewACategory";
        }

        categoryService.save(dto);
        return getAllCategories(model);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/delete/{id}")
    public String deleteCategory(@PathVariable Long id, Model model){
        try {
            categoryService.deleteCategoryById(id);
        } catch (DataIntegrityViolationException ex) { //Catch if deletion fails due to constraint
            model.addAttribute("deletionError", "Category is bound to a skill and cannot be deleted");
        }

        return getAllCategories(model);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/add")
    public String insertCategory(@Valid SkillCategoryDTO categoryDTO,
                                 BindingResult result,
                                 Model model) {
        try{
            categoryService.save(categoryDTO);
        } catch (Exception ex) {
            model.addAttribute("error", "A category must have a description");
        }
        return getAllCategories(model);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/skillPage/{id}")
    public String getACategoriesSkills(@PathVariable Long id, Model model){
        List<SkillDTO> skillDTOS = skillService.getSkillsByCategoryId(id);
        String categoryDescription = categoryService.getCategoryById(id).getDescription();

        categoryDescription += " Skills";

        model.addAttribute("skills", skillDTOS);
        model.addAttribute("categoryDescription", categoryDescription);

        return "viewSkillsByCategory";
    }

}
