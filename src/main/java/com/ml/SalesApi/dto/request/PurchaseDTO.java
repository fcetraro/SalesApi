package com.ml.SalesApi.dto.request;

import com.ml.SalesApi.dto.request.ArticlesRequestDTO;

import java.util.List;

public class PurchaseDTO {
    String userName;
    List<ArticlesRequestDTO> articles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ArticlesRequestDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesRequestDTO> articles) {
        this.articles = articles;
    }
}
