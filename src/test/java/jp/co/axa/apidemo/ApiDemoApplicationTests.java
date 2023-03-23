package jp.co.axa.apidemo;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {

    @Autowired
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testGetEmployees() throws Exception {
        Employee employee = new Employee();
        employee.setId((long) 1);
        employee.setName("Jijo");
        employee.setSalary(6000);
        employee.setDepartment("IT");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));

    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setId((long) 1);
        employee.setName("Jijo");
        employee.setSalary(6000);
        employee.setDepartment("IT");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Jijo")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary", Matchers.is(6000)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.department", Matchers.is("IT")));
    }

    @Test
    public void testSaveEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId((long) 1);
        employee.setName("Jijo");
        employee.setSalary(6000);
        employee.setDepartment("IT");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));

    }

    @Test
    public void testDeleteEmployee() throws Exception {

        Employee employee = new Employee();
        employee.setId((long) 1);
        employee.setName("Jijo");
        employee.setSalary(6000);
        employee.setDepartment("IT");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    // @Test
    // public void testUpdateEmployee() throws Exception {
    // Employee employee = new Employee();
    // employee.setName("Jijo");
    // employee.setSalary(6000);
    // employee.setDepartment("IT");

    // ObjectMapper mapper = new ObjectMapper();
    // String json = mapper.writeValueAsString(employee);

    // mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employees/1")
    // .contentType(MediaType.APPLICATION_JSON)
    // .content(json))
    // .andExpect(MockMvcResultMatchers.status().isOk());

    // mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/1"))
    // .andExpect(MockMvcResultMatchers.status().isOk())
    // .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Jijo")))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.salary", Matchers.is(6000)))
    // .andExpect(MockMvcResultMatchers.jsonPath("$.department",
    // Matchers.is("IT")));
    // }
}
