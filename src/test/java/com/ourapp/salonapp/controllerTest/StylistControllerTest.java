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

import com.ourapp.salonapp.config.SpringConfig;
import com.ourapp.salonapp.config.StylistSecurityConfiguration;
import com.ourapp.salonapp.controller.StylistRegistrationController;
import com.ourapp.salonapp.dto.Stylistdto;
import com.ourapp.salonapp.service.StylistLoginService;

@RunWith(SpringRunner.class)
@WebMvcTest(StylistRegistrationController.class)
@ContextConfiguration(classes= {StylistSecurityConfiguration.class,SpringConfig.class})
public class StylistControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
    private StylistLoginService stylistLoginService;

	String exampleJson = "{\"name\":\"aravindpc\",\"email\":\"aravindpcaravi333@gmail.com\",\"number\":12344,\"password\":\"a\"}";

	@Test
	public void createStylist() throws Exception {
		String mockout ="Registered";
		
		Mockito.when(stylistLoginService.createStylist(Mockito.any(Stylistdto.class))).thenReturn(mockout);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/stylistRegistration/stylistRegister").content(exampleJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		String output=response.getContentAsString();
        assertEquals(mockout,output);
		assertEquals(200, response.getStatus());
	}
}
