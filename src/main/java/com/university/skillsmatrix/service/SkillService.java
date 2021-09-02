package com.university.skillsmatrix.service;

import com.university.skillsmatrix.convertor.skill.DTOToSkillConvertor;
import com.university.skillsmatrix.convertor.skill.SkillToDTOConvertor;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.entity.Skill;
import com.university.skillsmatrix.exceptions.ResourceNotFoundException;
import com.university.skillsmatrix.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;
    private final SkillToDTOConvertor skillToDTOConvertor;
    private final DTOToSkillConvertor dtoToSkillConvertor;

    @Transactional
    public List<SkillDTO> getAllSkills(){
        Iterable<Skill> skillIterable = skillRepository.findAll();
        List<SkillDTO> skillDTOList = new ArrayList<>();

        for(Skill skill: skillIterable){
            SkillDTO dto = skillToDTOConvertor.convert(skill);
            skillDTOList.add(dto);
        }

        return skillDTOList;
    }

    public SkillDTO getSkillById(Long id){
        Optional<Skill> skill = skillRepository.findById(id);
        if(skill.isPresent()){
            return skillToDTOConvertor.convert(skill.get());
        } else {
            throw new ResourceNotFoundException("Skill id is not found", "Enter a valid skill id");
        }
    }

    public SkillDTO save(SkillDTO dto){
        Skill skill = dtoToSkillConvertor.convert(dto);
        skill = skillRepository.save(skill);
        return skillToDTOConvertor.convert(skill);
    }

    public void deleteSkillById(Long id){
        skillRepository.deleteById(id);
    }

    public List<SkillDTO> getSkillsByCategoryId(Long id){
        List<SkillDTO> skillDTOS = new ArrayList<>();
        List<Skill> skills = skillRepository.findSkillsByCategoryId(id);

        for(Skill skill: skills){
            SkillDTO dto = skillToDTOConvertor.convert(skill);
            skillDTOS.add(dto);
        }

        return skillDTOS;
    }
}
