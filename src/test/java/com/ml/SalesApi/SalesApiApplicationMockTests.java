package com.ml.SalesApi;

import com.ml.SalesApi.communicator.IProductApiCommunicator;
import com.ml.SalesApi.dao.IReceiptDAO;
import com.ml.SalesApi.dto.ArticleDTO;
import com.ml.SalesApi.dto.request.ArticlesRequestDTO;
import com.ml.SalesApi.dto.request.PurchaseDTO;
import com.ml.SalesApi.exception.concreteExceptions.ConnectionException;
import com.ml.SalesApi.service.IPurchaseService;
import com.ml.SalesApi.stubs.StubArticleDTO;
import com.ml.SalesApi.stubs.StubPurchaseDTO;
import com.ml.SalesApi.util.Assembler;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SalesApiApplicationMockTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private IReceiptDAO repository;

	@Autowired
	private IPurchaseService service;

	@MockBean
	private IProductApiCommunicator productApi;

	@Test
	void shouldPurchase() throws Exception {
		String url = "/api/v1/purchase-request";
		String expectedResult = "270.0";
		PurchaseDTO purchaseStub = StubPurchaseDTO.getStubPurchase();
		String requestJson = "{"+Assembler.transformToStringJson(purchaseStub)+"}";
		List<ArticleDTO> articles = StubArticleDTO.getStubArticles(3,1);
		when(productApi.getProducts(anyList())).thenReturn(articles);
		this.mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(expectedResult)));
	}
	@Test
	void shouldOutOfStockPurchase() throws Exception {
		String url = "/api/v1/purchase-request";
		PurchaseDTO purchaseStub = StubPurchaseDTO.getStubPurchase();
		String requestJson = "{"+Assembler.transformToStringJson(purchaseStub)+"}";
		List<ArticleDTO> articles = StubArticleDTO.getStubArticles(3,1);
		ArticleDTO outOfStock = StubArticleDTO.getStubArticle(1);
		outOfStock.setQuantity(0);
		articles.set(0,outOfStock);
		when(productApi.getProducts(anyList())).thenReturn(articles);
		this.mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	@Test
	void shouldNotFoundProductPurchase() throws Exception {
		String url = "/api/v1/purchase-request";
		PurchaseDTO purchaseStub = StubPurchaseDTO.getStubPurchase();
		String requestJson = "{"+Assembler.transformToStringJson(purchaseStub)+"}";
		List<ArticleDTO> articles = StubArticleDTO.getStubArticles(2,1);
		when(productApi.getProducts(anyList())).thenReturn(articles);
		this.mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	@Test
	void shouldConnectionLost() throws Exception {
		String url = "/api/v1/purchase-request";
		PurchaseDTO purchaseStub = StubPurchaseDTO.getStubPurchase();
		String requestJson = "{"+Assembler.transformToStringJson(purchaseStub)+"}";
		List<ArticleDTO> articles = StubArticleDTO.getStubArticles(2,1);
		when(productApi.getProducts(anyList())).thenThrow(ConnectionException.class);
		this.mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
}
