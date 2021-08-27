package com.university.skillsmatrix.controllers;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StaffControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test01_When_Given_A_Request_For_All_Staff_Then_Return_All() throws Exception{
        mockMvc.perform(get("/staff/"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllStaff"))
                .andExpect(model().attributeExists("staffList"));
    }
}
