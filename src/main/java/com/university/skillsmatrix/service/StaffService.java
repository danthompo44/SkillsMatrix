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
@Transactional
@RequiredArgsConstructor
public class StaffService {
    //dependencies
    private final StaffRepository staffRepository;
    private final StaffToDTOConvertor staffConvertor;

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
}
