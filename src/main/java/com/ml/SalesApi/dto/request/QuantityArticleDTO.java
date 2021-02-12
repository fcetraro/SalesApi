package com.ml.SalesApi.dto.request;

public class QuantityArticleDTO {
    int id, quantityBought;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantityBought() {
        return quantityBought;
    }

    public void setQuantityBought(int quantityBought) {
        this.quantityBought = quantityBought;
    }
}
