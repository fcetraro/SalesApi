package com.ml.SalesApi.service;

import com.ml.SalesApi.dto.request.PurchaseDTO;
import com.ml.SalesApi.dto.ReceiptDTO;
import com.ml.SalesApi.dto.response.ReceiptResponseDTO;

public interface IPurchaseService {
    ReceiptResponseDTO purchaseProducts(PurchaseDTO purchase);
}
