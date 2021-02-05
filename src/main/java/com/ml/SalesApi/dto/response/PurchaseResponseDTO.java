package com.ml.SalesApi.dto.response;

import com.ml.SalesApi.dto.ReceiptDTO;

import java.util.List;

public class PurchaseResponseDTO {
    ReceiptResponseDTO receipt;
    StatusCodeDTO statusCode;

    public ReceiptResponseDTO getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptResponseDTO receipt) {
        this.receipt = receipt;
    }

    public StatusCodeDTO getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCodeDTO statusCode) {
        this.statusCode = statusCode;
    }
}
