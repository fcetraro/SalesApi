package com.ml.SalesApi.dto.request;

import java.util.List;

public class BoughtArticlesDTO {
    List<QuantityArticleDTO> articles;

    public List<QuantityArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<QuantityArticleDTO> articles) {
        this.articles = articles;
    }
}
