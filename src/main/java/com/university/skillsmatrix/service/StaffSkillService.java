package com.university.skillsmatrix.service;

import com.university.skillsmatrix.convertor.staffSkill.DTOToStaffSkillConvertor;
import com.university.skillsmatrix.convertor.staffSkill.StaffSkillDTOToStaffSkillIdDTO;
import com.university.skillsmatrix.convertor.staffSkill.StaffSkillToDTOConvertor;
import com.university.skillsmatrix.domain.StaffSkillDTO;
import com.university.skillsmatrix.domain.StaffSkillIdDTO;
import com.university.skillsmatrix.entity.StaffSkill;
import com.university.skillsmatrix.exceptions.ResourceNotFoundException;
import com.university.skillsmatrix.repository.StaffSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffSkillService {
    private final StaffSkillRepository staffSkillRepository;
    private final StaffSkillToDTOConvertor staffSkillConvertor;
    private final StaffSkillDTOToStaffSkillIdDTO staffSkillIdConvertor;
    private final DTOToStaffSkillConvertor dtoToStaffSkillConvertor;

    @Transactional
    public List<StaffSkillDTO> getAllStaffSkills(){
        Iterable<StaffSkill> staffSkillIterable = staffSkillRepository.findAll();
        List<StaffSkillDTO> staffSkillDTOList = new ArrayList<>();

        for(StaffSkill staffSkill: staffSkillIterable){
            StaffSkillDTO dto = staffSkillConvertor.convert(staffSkill);
            staffSkillDTOList.add(dto);
        }

        return staffSkillDTOList;
    }

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

    public StaffSkillDTO findById(Long id){
        Optional<StaffSkill> optional = staffSkillRepository.findById(id);

        if(optional.isPresent()){
            return staffSkillConvertor.convert(optional.get());
        } else{
            throw new ResourceNotFoundException("Staff skill not found", "Enter a valid id");
        }

    }

    public StaffSkillIdDTO findIdDtoById(Long id){
        try{
            StaffSkillDTO staffSkillDTO = findById(id);
            return staffSkillIdConvertor.convert(staffSkillDTO);

        } catch(ResourceNotFoundException ex){
            throw ex;
        }
    }

    public StaffSkillDTO save(StaffSkillDTO dto){
        StaffSkill staffSkill = dtoToStaffSkillConvertor.convert(dto);
        staffSkill = staffSkillRepository.save(staffSkill);
        return staffSkillConvertor.convert(staffSkill);
    }

    public void deleteStaffSKillById(Long id){
        staffSkillRepository.deleteById(id);
    }
}
