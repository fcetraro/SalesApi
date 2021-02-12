package com.ml.SalesApi.communicator;

import com.ml.SalesApi.dto.ArticleDTO;
import com.ml.SalesApi.dto.request.ArticlesRequestDTO;
import com.ml.SalesApi.dto.request.QuantityArticleDTO;

import java.util.List;

public interface IProductApiCommunicator {
    List<ArticleDTO> getProducts(List<ArticlesRequestDTO> articles);
    void modifyProducts(List<QuantityArticleDTO> articles);
}
