package com.university.skillsmatrix.service;

import com.university.skillsmatrix.convertor.staff.StaffToDTOConvertor;
import com.university.skillsmatrix.domain.StaffDTO;
import com.university.skillsmatrix.entity.Staff;
import com.university.skillsmatrix.exceptions.ResourceNotFoundException;
import com.university.skillsmatrix.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffService {
    //dependencies
    private final StaffRepository staffRepository;
    private final StaffToDTOConvertor staffConvertor;

    @Transactional
    public List<StaffDTO> getAllStaff(){
        Iterable<Staff> staffIterable = staffRepository.findAll();
        List<StaffDTO> staffDTOList = new ArrayList<>();

        for(Staff staff: staffIterable){
            StaffDTO dto = staffConvertor.convert(staff);
            staffDTOList.add(dto);
        }

        return staffDTOList;
    }

    public StaffDTO getStaffById(Long id){
        Optional<Staff> staff = staffRepository.findById(id);

        if(staff.isPresent()){
            return staffConvertor.convert(staff.get());
        }
        else{
            throw new ResourceNotFoundException("Staff member is not found", "Enter a valid id");
        }
    }

    public StaffDTO getStaffByAppUserId(Long id){
        Optional<Staff> staff = Optional.ofNullable(staffRepository.findStaffByUserId(id));
        if(staff.isPresent()){
            return staffConvertor.convert(staff.get());
        }
        else{
            throw new ResourceNotFoundException("Manager is not found", "Enter a valid id");
        }
    }

    public List<StaffDTO> getStaffBySkillId(Long id){
        Iterable<Staff> staffIterable = staffRepository.findStaffsBySkillsId(id);
        List<StaffDTO> staff = new ArrayList<>();

        for(Staff s : staffIterable){
            StaffDTO dto = staffConvertor.convert(s);
            staff.add(dto);
        }

        return staff;
    }

    public List<StaffDTO> getStaffByManagerId(Long id){
        Iterable<Staff> staffIterable = staffRepository.findStaffByManagerId(id);
        List<StaffDTO> staff = new ArrayList<>();

        for(Staff s : staffIterable){
            StaffDTO dto = staffConvertor.convert(s);
            staff.add(dto);
        }

        return staff;
    }
}
