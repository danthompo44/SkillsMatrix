package com.university.skillsmatrix.controllers;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SkillControllerTest {
    private final String REDIRECTED_LOGIN = "http://localhost/login";

    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webAppContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test01_When_Given_A_Request_For_All_Skills_With_A_Role_Of_Admin_Then_Return_All() throws Exception{
        mockMvc.perform(get("/skill/admin/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllSkills"))
                .andExpect(model().attributeExists("skillList"))
                .andExpect(model().attributeExists("skillCategory"))
                .andExpect(model().attributeExists("skillDTO"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test02_When_Given_A_Request_For_All_Skills_With_A_Role_Of_User_Then_Return_Error() throws Exception{
        mockMvc.perform(get("/skill/admin/all"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test03_When_Given_A_Request_For_All_Skills_With_No_Role_Then_Return_Login() throws Exception{
        mockMvc.perform(get("/skill/admin/all"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test04_When_Given_A_Request_For_Edit_Skills_Page_With_A_Role_Of_Admin_Then_Return_Edit_Page() throws Exception{
        mockMvc.perform(get("/skill/admin/editPage/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewASkill"))
                .andExpect(model().attributeExists("skill"))
                .andExpect(model().attributeExists("skillCategory"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test05_When_Given_A_Request_For_Edit_Skills_Page_With_A_Role_Of_User_Then_Return_Client_Error() throws Exception{
        mockMvc.perform(get("/skill/admin/editPage/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test06_When_Given_A_Request_For_Edit_Skills_Page_With_No_Role_Then_Return_Login() throws Exception{
        mockMvc.perform(get("/skill/admin/editPage/1"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test07_When_Given_A_Request_To_Update_A_Skill_With_A_Role_Of_Admin_Then_Return_Get_All_Skills() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/skill/admin/update/1")
                .with(csrf())
                .param("name", "Java")
                .param("category.id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllSkills"))
                .andExpect(model().attributeExists("skillList"))
                .andExpect(model().attributeExists("skillCategory"))
                .andExpect(model().attributeExists("skillDTO"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test08_When_Given_A_Request_To_Update_A_Skill_With_A_Role_Of_User_Then_Return_Client_Error() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/skill/admin/update/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test09_When_Given_A_Request_To_Update_A_Skill_With_No_Role_Then_Return_Login() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/skill/admin/update/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test (expected = NestedServletException.class)
    @WithMockUser(roles = "ADMIN")
    public void test10_When_Given_An_Invalid_Request_To_Update_A_Skill_With_A_Role_Of_Admin_Then_Return_Get_All_Skills() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/skill/admin/update/1")
                .with(csrf())
                .param("name", "Java"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewASkill"))
                .andExpect(model().attributeExists("skill"))
                .andExpect(model().attributeExists("skillCategory"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test11_When_Given_A_Valid_Request_To_Delete_A_Skill_As_An_Admin_Return_View_All_Skills() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/skill/admin/delete/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllSkills"))
                .andExpect(model().attributeExists("skillList"))
                .andExpect(model().attributeExists("skillCategory"))
                .andExpect(model().attributeExists("skillDTO"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test12_When_Given_A_Valid_Request_To_Delete_A_Skill_As_A_User_Return_Client_Error() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/skill/admin/delete/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test13_When_Given_A_Valid_Request_To_Delete_A_Skill_With_No_Role_Return_Login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/skill/admin/delete/1"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test (expected = NestedServletException.class)
    @WithMockUser(roles = "ADMIN")
    public void test14_When_Given_An_Invalid_Request_To_Delete_A_Skill_As_An_Admin_Return_View_All_Skills_With_Deletion_Error() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/skill/admin/delete/0"))
                .andDo(print())
                .andExpect(view().name("viewAllSkills"))
                .andExpect(model().attributeExists("skillList"))
                .andExpect(model().attributeExists("skillCategory"))
                .andExpect(model().attributeExists("skillDTO"))
                .andExpect(model().attributeExists("deletionError"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test15_When_Given_A_Valid_Request_To_Insert_Skill_With_Role_Admin_Return_Get_All_Skills() throws Exception {
        mockMvc.perform(post("/skill/admin/add")
                .with(csrf())
                .param("name", "Java")
                .param("category.id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllSkills"))
                .andExpect(model().attributeExists("skillList"))
                .andExpect(model().attributeExists("skillCategory"))
                .andExpect(model().attributeExists("skillDTO"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test16_When_Given_A_Valid_Request_To_Insert_Skill_With_Role_User_Return_Client_Error() throws Exception {
        mockMvc.perform(post("/skill/admin/add")
                .with(csrf())
                .param("name", "Java")
                .param("category.id", "1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test17_When_Given_A_Valid_Request_To_Insert_Skill_With_No_Role_Return_Login() throws Exception {
        mockMvc.perform(post("/skill/admin/add")
                .with(csrf())
                .param("name", "Java")
                .param("category.id", "1"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test18_When_Given_An_Invalid_Request_To_Insert_Skill_With_Role_Admin_Return_Get_All_Skills_With_Error() throws Exception {
        mockMvc.perform(post("/skill/admin/add")
                .with(csrf())
                .param("name", "Java"))
                .andDo(print())
                .andExpect(view().name("viewAllSkills"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attributeExists("skillList"))
                .andExpect(model().attributeExists("skillCategory"))
                .andExpect(model().attributeExists("skillDTO"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test19_When_Given_A_Valid_Request_To_Get_Staff_By_Skill_Id_As_An_Admin_Return_View_Staff_By_Skill() throws Exception {
        mockMvc.perform(get("/skill/admin/staffPage/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewStaffBySkill"))
                .andExpect(model().attributeExists("staffList"))
                .andExpect(model().attributeExists("skillTitle"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test20_When_Given_A_Valid_Request_To_Get_Staff_By_Skill_Id_As_A_User_Return_Client_Error() throws Exception {
        mockMvc.perform(get("/skill/admin/staffPage/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test21_When_Given_A_Valid_Request_To_Get_Staff_By_Skill_Id_With_No_Role_Return_Login_Redirect() throws Exception {
        mockMvc.perform(get("/skill/admin/staffPage/1"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test22_When_Given_An_Invalid_Request_To_Get_Staff_By_Skill_Id_As_An_Admin_Return_View_Staff_By_Skill() throws Exception {
        mockMvc.perform(get("/skill/admin/staffPage/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test23_When_Given_A_Request_To_View_Staff_Skills_With_User_Role_Return_View_My_Skills() throws Exception {
        mockMvc.perform(get("/skill/view"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewMySkills"))
                .andExpect(model().attributeExists("staffSkillList"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test24_When_Given_A_Request_To_View_Staff_Skills_With_Admin_Role_Return_Client_Error() throws Exception {
        mockMvc.perform(get("/skill/view"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test25_When_Given_A_Request_To_View_Staff_Skills_With_No_Role_Return_Login_Redirect() throws Exception {
        mockMvc.perform(get("/skill/view"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test26_When_Given_A_Valid_Request_To_View_Staff_Skill_Edit_Page_With_Role_User_Return_Edit_Staff_Skill() throws Exception {
        mockMvc.perform(get("/skill/editSkillPage/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editStaffSkill"))
                .andExpect(model().attributeExists("staffSkillIdDTO"))
                .andExpect(model().attributeExists("titleString"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test27_When_Given_A_Valid_Request_To_View_Staff_Skill_Edit_Page_With_Role_Admin_Return_Client_Error() throws Exception {
        mockMvc.perform(get("/skill/editSkillPage/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test28_When_Given_A_Valid_Request_To_View_Staff_Skill_Edit_Page_With_No_Role_Return_Login_Redirect() throws Exception {
        mockMvc.perform(get("/skill/editSkillPage/1"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test29_When_Given_An_Invalid_Request_To_View_Staff_Skill_Edit_Page_With_Role_User_Return_Edit_Staff_Skill() throws Exception {
        mockMvc.perform(get("/skill/editSkillPage/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test30_When_Given_A_Valid_Request_To_Update_Staff_Skill_With_Role_User_Return_View_Skills() throws Exception {
        mockMvc.perform(post("/skill/staff/update/1")
                .with(csrf())
                .param("expiryDate", "2022-12-29")
                .param("skillStrength", "9")
                .param("staffId", "1")
                .param("skillId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewMySkills"))
                .andExpect(model().attributeExists("staffSkillList"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test31_When_Given_A_Valid_Request_To_Update_Staff_Skill_With_Role_Admin_Return_Client_Error() throws Exception {
        mockMvc.perform(post("/skill/staff/update/1")
                .with(csrf())
                .param("expiryDate", "2022-12-29")
                .param("skillStrength", "9")
                .param("staffId", "1")
                .param("skillId", "1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test32_When_Given_A_Valid_Request_To_Update_Staff_Skill_With_No_Role_Return_Login_Redirect() throws Exception {
        mockMvc.perform(post("/skill/staff/update/1")
                .with(csrf())
                .param("expiryDate", "2022-12-29")
                .param("skillStrength", "9")
                .param("staffId", "1")
                .param("skillId", "1"))
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test33_When_Given_An_Invalid_Request_To_Update_Staff_Skill_With_Role_User_Return_Error() throws Exception {
        mockMvc.perform(post("/skill/staff/update/1")
                .with(csrf())
                .param("expiryDate", "2022-12-29")
                .param("skillStrength", "9")
                .param("staffId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test34_When_Given_A_Valid_Request_Delete_Staff_Skill_Role_User_Then_View_My_Skills() throws Exception {
        mockMvc.perform(get("/skill/staff/delete/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewMySkills"))
                .andExpect(model().attributeExists("staffSkillList"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test35_When_Given_A_Valid_Request_Delete_Staff_Skill_Role_Admin_Then_Client_Error() throws Exception {
        mockMvc.perform(get("/skill/staff/delete/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test36_When_Given_A_Valid_Request_Delete_Staff_Skill_No_Role_Then_Login_Redirect() throws Exception {
        mockMvc.perform(get("/skill/staff/delete/1"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test (expected = NestedServletException.class)
    @WithMockUser(roles = "USER")
    public void test37_When_Given_An_Invalid_Request_Delete_Staff_Skill_Role_User_View_Skills_With_Error() throws Exception {
        mockMvc.perform(get("/skill/staff/delete/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewMySkills"))
                .andExpect(model().attributeExists("staffSkillList"))
                .andExpect(model().attributeExists("deletionError"));
    }
}
