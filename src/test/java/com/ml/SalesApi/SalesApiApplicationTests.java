package com.ml.SalesApi;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SalesApiApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Test
	void shouldPurchase() throws Exception {
		String url = "/api/v1/purchase-request";
		String requestJson = "{\"userName\":\"arjonamiguel@gmail.com\",\"articles\":[{\"productId\":3,\"discount\"" +
				":100,\"quantity\":1},{\"productId\":1,\"discount\":50,\"quantity\":1}]}";
		String expectedResult = "6250";
		this.mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(expectedResult)));
	}
	@Test
	void shouldOutOfStockPurchase() throws Exception {
		String url = "/api/v1/purchase-request";
		String requestJson = "{\"userName\":\"arjonamiguel@gmail.com\",\"articles\":[{\"productId\":3,\"discount\"" +
				":100,\"quantity\":1},{\"productId\":10,\"discount\":50,\"quantity\":200}]}";
		String expectedResult = "6250";
		this.mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
}
