package com.examly.springapp;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class SpringappApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
	@Transactional
    public void BE_Add_Employee() throws Exception {
        String newEmployee = "{\"username\":\"test\",\"password\":\"Test@123\",\"email\":\"test@123\",\"mobileNumber\":\"9876543210\",\"vehicleModel\":\"swift dezire\",\"vehicleNumber\":\"TN38 N3344\",\"verified\":\"true\",\"active\":\"true\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
		.contentType(MediaType.APPLICATION_JSON)
		.content(newEmployee)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andReturn();
    }
	
	@Test
	@Transactional
    public void BE_Get_Employee() throws Exception {
	 	mockMvc.perform(MockMvcRequestBuilders.get("/admin/getEmployee")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
		.andReturn();
    }
	

	@Test
	@Transactional
    public void BE_Update_Employee() throws Exception {
        String newEmployee = "{\"customerId\":\"01\",\"customerName\":\"test\",\"emailId\":\"test@gmail.com\",\"mobileNumber\":\"9786534201\",\"status\":\"true\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/editCustomer")
		.param("customerId","01")
		.contentType(MediaType.APPLICATION_JSON)
		.content(newEmployee)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andReturn();
    }
	
	@Test
	@Transactional
    public void BE_Add_Route() throws Exception {
        String newRoute = "{\"routeId\":\"01\",\"startPoint\":\"Place-A\",\"endPoint\":\"Place-B\",\"distance\":\"100\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/addRoutes")
		.contentType(MediaType.APPLICATION_JSON)
		.content(newRoute)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andReturn();
    }

	@Test
	@Transactional
    public void BE_Update_Route() throws Exception {
        String newRoute = "{\"routeId\":\"01\",\"startPoint\":\"Place-A\",\"endPoint\":\"Place-B\",\"distance\":\"110\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/admin/editRoutes")
		.param("routeId","01")
		.contentType(MediaType.APPLICATION_JSON)
		.content(newRoute)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andReturn();
    }
}
