package com.ml.SalesApi.communicator.implementation;

import com.ml.SalesApi.communicator.IProductApiCommunicator;
import com.ml.SalesApi.dto.ArticleDTO;
import com.ml.SalesApi.dto.request.ArticlesDTO;
import com.ml.SalesApi.dto.request.ArticlesRequestDTO;
import com.ml.SalesApi.dto.request.BoughtArticlesDTO;
import com.ml.SalesApi.dto.request.QuantityArticleDTO;
import com.ml.SalesApi.exception.concreteExceptions.ConnectionException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ProductApiCommunicator implements IProductApiCommunicator {
    final String uri = "http://localhost:8080/api/v1";
    final String articlesEndpoint = "/articles";

    private String getRequestParams(List<ArticlesRequestDTO> articles){
        String requestParams = "";
        boolean first = true;
        for (ArticlesRequestDTO article:articles) {
            char separator  = '&';
            if(first){
                first=false;
                separator='?';
            }
            requestParams = requestParams+separator+"id="+article.getProductId();
        }
        return requestParams;
    }

    public List<ArticleDTO> getProducts(List<ArticlesRequestDTO> articles) {
        String getProductsUri = uri + articlesEndpoint + "/search" + getRequestParams(articles);
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(getProductsUri, ArticlesDTO.class).getArticles();
        } catch (Exception e) {
            throw new ConnectionException("Error al comunicar con ProductsApi: "+e.getMessage(),e);
        }
    }

    public void modifyProducts(List<QuantityArticleDTO> articles) {
        String getProductsUri = uri + articlesEndpoint + "/buy";
        BoughtArticlesDTO bought = new BoughtArticlesDTO();
        bought.setArticles(articles);
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(getProductsUri, bought);
        } catch (Exception e) {
            throw new ConnectionException("Error al comunicar con ProductsApi: "+e.getMessage(),e);
        }
    }
}
