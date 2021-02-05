package com.ml.SalesApi.controller;

import com.ml.SalesApi.dto.request.PurchaseDTO;
import com.ml.SalesApi.dto.ReceiptDTO;
import com.ml.SalesApi.dto.response.PurchaseResponseDTO;
import com.ml.SalesApi.dto.response.ReceiptResponseDTO;
import com.ml.SalesApi.dto.response.StatusCodeDTO;
import com.ml.SalesApi.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PurchaseController {
    @Autowired
    private IPurchaseService service;
    @PostMapping("/purchase-request")
    public PurchaseResponseDTO purchase(@RequestBody PurchaseDTO purchase){
        return service.purchaseProducts(purchase);
    }
}
