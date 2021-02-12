package com.ml.SalesApi.stubs;

import com.ml.SalesApi.dto.request.ArticlesRequestDTO;
import com.ml.SalesApi.dto.request.PurchaseDTO;

import java.util.ArrayList;
import java.util.List;

public class StubPurchaseDTO {
    public static PurchaseDTO getStubPurchase(){
        PurchaseDTO stubPurchase = new PurchaseDTO();
        stubPurchase.setUserName("arjonamiguel@gmail.com");
        List<ArticlesRequestDTO> list = new ArrayList<>();
        list.add(StubArticlesRequestDTO.getStubArticleRequest(1,10,1));
        list.add(StubArticlesRequestDTO.getStubArticleRequest(2,10,1));
        stubPurchase.setArticles(list);
        return stubPurchase;
    }
}
