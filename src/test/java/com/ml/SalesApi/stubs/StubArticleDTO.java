package com.ml.SalesApi.stubs;

import com.ml.SalesApi.dto.ArticleDTO;

import java.util.ArrayList;
import java.util.List;

public class StubArticleDTO {
    public static ArticleDTO getStubArticle(int id){
        ArticleDTO newStub = new ArticleDTO();
        newStub.setId(id);
        newStub.setPrice(100*id);
        newStub.setQuantity(5+id);
        newStub.setBrand("testBrand");
        newStub.setCategory("testCategory");
        newStub.setName("testName");
        newStub.setPrestige(id%6);
        newStub.setFreeShipping(true);
        return newStub;
    }
    public static List<ArticleDTO> getStubArticles(int end, int start){
        List<ArticleDTO> newStubs = new ArrayList<>();
        for (int i = start; i < end; i++) {
            newStubs.add(getStubArticle(i));
        }
        return newStubs;
    }
}
