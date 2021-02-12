package com.ml.SalesApi.stubs;

import com.ml.SalesApi.dto.request.ArticlesRequestDTO;

public class StubArticlesRequestDTO {
    public static ArticlesRequestDTO getStubArticleRequest(int id, int discount, int quantity){
        ArticlesRequestDTO article = new ArticlesRequestDTO();
        article.setDiscount(discount);
        article.setProductId(id);
        article.setQuantity(quantity);
        return article;
    }
}
