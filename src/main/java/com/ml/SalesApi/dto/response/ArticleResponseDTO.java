package com.ml.SalesApi.dto.response;

import com.ml.SalesApi.dto.ArticleDTO;
import com.ml.SalesApi.dto.request.ArticlesDTO;

public class ArticleResponseDTO {
    String name, category, brand;
    int price, prestige;
    boolean freeShipping;

    public ArticleResponseDTO(ArticleDTO article) {
        this.name = article.getName();
        this.category = article.getCategory();
        this.brand = article.getBrand();
        this.price = article.getPrice();
        this.prestige = article.getPrestige();
        this.freeShipping = article.isFreeShipping();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }
}
