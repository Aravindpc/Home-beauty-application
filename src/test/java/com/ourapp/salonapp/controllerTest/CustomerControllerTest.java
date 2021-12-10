package com.ourapp.salonapp.controllerTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ourapp.salonapp.config.CustomerSecurityConfiguration;
import com.ourapp.salonapp.config.SpringConfig;
import com.ourapp.salonapp.controller.RegistrationController;
import com.ourapp.salonapp.dto.Customerdto;
import com.ourapp.salonapp.service.CustomerLoginService;

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
@ContextConfiguration(classes= {CustomerSecurityConfiguration.class,SpringConfig.class})
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
    private CustomerLoginService customerLoginService;

	String exampleJson = "{\"name\":\"aravindpc\",\"email\":\"aravindpcaravi333@gmail.com\",\"number\":12344,\"address\":\"MNV\",\"password\":\"a\"}";

	@Test
	public void createCustomer() throws Exception {
		String mockout ="Registered";
		
		Mockito.when(customerLoginService.createCustomer(Mockito.any(Customerdto.class))).thenReturn(mockout);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/registration/register").content(exampleJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String output=response.getContentAsString();
        assertEquals(mockout,output);
		assertEquals(200, response.getStatus());
	}
}
