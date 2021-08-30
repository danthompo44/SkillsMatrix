package com.university.skillsmatrix.service;

import com.university.skillsmatrix.convertor.skill.SkillToDTOConvertor;
import com.university.skillsmatrix.domain.SkillDTO;
import com.university.skillsmatrix.entity.Skill;
import com.university.skillsmatrix.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;
    private final SkillToDTOConvertor skillToDTOConvertor;

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
}
