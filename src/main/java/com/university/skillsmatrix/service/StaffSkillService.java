package com.university.skillsmatrix.service;

import com.university.skillsmatrix.convertor.staffSkill.StaffSkillToDTOConvertor;
import com.university.skillsmatrix.domain.StaffSkillDTO;
import com.university.skillsmatrix.entity.StaffSkill;
import com.university.skillsmatrix.repository.StaffSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffSkillService {
    private final StaffSkillRepository staffSkillRepository;
    private final StaffSkillToDTOConvertor staffSkillConvertor;

    @Transactional
    public List<StaffSkillDTO> getStaffSkillsByStaffId(Long id){
        Iterable<StaffSkill> staffSkillIterable = staffSkillRepository.findAllByStaffId(id);
        List<StaffSkillDTO> staffSkillDTOList = new ArrayList<>();

        for(StaffSkill staffSkill: staffSkillIterable){
            StaffSkillDTO dto = staffSkillConvertor.convert(staffSkill);
            staffSkillDTOList.add(dto);
        }

        return staffSkillDTOList;
    }
}
