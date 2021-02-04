package com.ml.SalesApi.dto.request;

import com.ml.SalesApi.dto.ArticleDTO;

import java.util.List;

public class ArticlesDTO {
   List<ArticleDTO> articles;

   public List<ArticleDTO> getArticles() {
      return articles;
   }

   public void setArticles(List<ArticleDTO> articles) {
      this.articles = articles;
   }
}
