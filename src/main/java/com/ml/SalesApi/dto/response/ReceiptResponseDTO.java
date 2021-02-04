package com.ml.SalesApi.dto.response;

import com.ml.SalesApi.dto.ArticleDTO;
import com.ml.SalesApi.dto.ReceiptDTO;

import java.util.List;

public class ReceiptResponseDTO {
    String status;
    double totalFromThisPurchase, total;
    int id;
    String email;
    List<ReceiptDTO> receipts;

    public ReceiptResponseDTO(ReceiptDTO thisReceipt, double total, List<ReceiptDTO> receipts) {
        this.status = thisReceipt.getStatus();
        this.totalFromThisPurchase = thisReceipt.getTotal();
        this.total = total;
        this.id = thisReceipt.getId();
        this.email = thisReceipt.getEmail();
        this.receipts = receipts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalFromThisPurchase() {
        return totalFromThisPurchase;
    }

    public void setTotalFromThisPurchase(double totalFromThisPurchase) {
        this.totalFromThisPurchase = totalFromThisPurchase;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ReceiptDTO> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<ReceiptDTO> receipts) {
        this.receipts = receipts;
    }
}
