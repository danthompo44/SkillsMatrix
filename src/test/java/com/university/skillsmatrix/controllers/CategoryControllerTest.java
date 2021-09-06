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
public class CategoryControllerTest {
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
    public void test01_When_Given_A_Request_For_All_Categories_With_A_Role_Of_Admin_Then_Return_All() throws Exception{
        mockMvc.perform(get("/category/admin/all/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllCategories"))
                .andExpect(model().attributeExists("categoryList"))
                .andExpect(model().attributeExists("categoryDTO"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test02_When_Given_A_Request_For_All_Categories_With_A_Role_Of_User_Then_Return_Client_Error() throws Exception{
        mockMvc.perform(get("/category/admin/all/"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test03_When_Given_A_Request_For_All_Categories_With_No_Role_Then_Redirect() throws Exception{
        mockMvc.perform(get("/category/admin/all/"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test04_When_Given_A_Request_For_Category_Edit_Page_With_A_Role_Of_Admin_Then_Return_Page() throws Exception{
        mockMvc.perform(get("/category/admin/editPage/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewACategory"))
                .andExpect(model().attributeExists("category"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test05_When_Given_A_Request_For_Category_Edit_Page_With_A_Role_Of_User_Then_Client_Error() throws Exception{
        mockMvc.perform(get("/category/admin/editPage/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test06_When_Given_A_Request_For_Category_Edit_Page_With_A_Role_Of_Admin_But_Category_Not_Exist_Then_Error() throws Exception{
        mockMvc.perform(get("/category/admin/editPage/99"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    @Test
    public void test07_When_Given_A_Request_For_Category_Edit_Page_With_No_Role_Then_Redirect() throws Exception{
        mockMvc.perform(get("/category/admin/editPage/1"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test08_When_Given_A_Request_For_Update_Category_With_A_Role_Of_Admin_Then_Return_All_Categories() throws Exception{
        mockMvc.perform(post("/category/admin/update/1")
                .with(csrf()).param("description", "description"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllCategories"))
                .andExpect(model().attributeExists("categoryList"))
                .andExpect(model().attributeExists("categoryDTO"));

    }

    @Test
    @WithMockUser(roles = "USER")
    public void test09_When_Given_A_Request_For_Update_Category_With_A_Role_Of_User_Then_Return_Client_Error() throws Exception{
        mockMvc.perform(post("/category/admin/update/1")
                .with(csrf()).param("description", "description"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void test10_When_Given_A_Request_For_Update_Category_With_No_Role_Then_Redirect() throws Exception{
        mockMvc.perform(get("/category/admin/update/1"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test11_When_Given_A_Request_For_Update_Category_With_A_Role_Of_Admin_But_No_Form_Data() throws Exception{
        mockMvc.perform(post("/category/admin/update/1")
                .with(csrf()))
                .andDo(print())
                .andExpect(view().name("viewACategory"))
                .andExpect(model().attributeExists("category"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test12_When_Given_A_Request_To_Delete_A_Category_With_A_Role_Of_Admin_Then_Redirect_To_Get_All_Categories() throws Exception {
        mockMvc.perform(get("/category/admin/delete/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllCategories"))
                .andExpect(model().attributeExists("categoryList"))
                .andExpect(model().attributeExists("categoryDTO"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test13_When_Given_A_Request_To_Delete_A_Category_With_A_Role_Of_User_Then_Client_Error() throws Exception {
        mockMvc.perform(get("/category/admin/delete/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test14_When_Given_A_Request_To_Delete_A_Category_With_No_Role_Then_Redirect_To_Login() throws Exception {
        mockMvc.perform(get("/category/admin/delete/1"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test (expected = NestedServletException.class)
    @WithMockUser(roles = "ADMIN")
    public void test15_When_Given_A_Request_To_Delete_An_Invalid_Category_Id_With_A_Role_Of_Admin_Then_Redirect_To_Get_All_Categories() throws Exception {
        mockMvc.perform(get("/category/admin/delete/0"))
                .andDo(print())
                .andExpect(model().attributeExists("deletionError","Unable to delete category"))
                .andExpect(view().name("viewAllCategories"))
                .andExpect(model().attributeExists("categoryList"))
                .andExpect(model().attributeExists("categoryDTO"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test16_When_Given_A_Request_To_Insert_Category_With_Valid_Fields_Return_Get_All_Categories() throws Exception {
        mockMvc.perform(post("/category/admin/add/")
                .with(csrf())
                .param("description", "description"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllCategories"))
                .andExpect(model().attributeExists("categoryList"))
                .andExpect(model().attributeExists("categoryDTO"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test17_When_Given_A_Request_To_Insert_Category_With_Valid_Fields_As_User_Return_Client_Error() throws Exception {
        mockMvc.perform(post("/category/admin/add/")
                .with(csrf())
                .param("description", "description"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test18_When_Given_A_Request_To_Insert_Category_With_No_Admin_Role_Redirect() throws Exception {
        mockMvc.perform(post("/category/admin/add/")
                .with(csrf()))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test19_When_Given_A_Request_To_Get_Categories_Skills_With_Admin_Role_Return_Expected() throws Exception {
        mockMvc.perform(get("/category/admin/skillPage/1"))
                .andDo(print())
                .andExpect(view().name("viewSkillsByCategory"))
                .andExpect(model().attributeExists("skills"))
                .andExpect(model().attributeExists("categoryDescription"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void test20_When_Given_A_Request_To_Get_Categories_Skills_With_User_Role_Return_Client_Error() throws Exception {
        mockMvc.perform(get("/category/admin/skillPage/1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void test21_When_Given_A_Request_To_Get_Categories_Skills_With_No_Admin_Role_Redirect() throws Exception {
        mockMvc.perform(get("/category/admin/skillPage/1"))
                .andDo(print())
                .andExpect(redirectedUrl(REDIRECTED_LOGIN))
                .andExpect(status().is3xxRedirection());
    }
}
