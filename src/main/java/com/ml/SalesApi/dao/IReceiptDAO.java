package com.ml.SalesApi.dao;

import com.ml.SalesApi.dto.ReceiptDTO;

import java.util.List;

public interface IReceiptDAO {
    ReceiptDTO addReceipt(ReceiptDTO newReceipt);
    List<ReceiptDTO> getReceiptsByEmail(String email);
}
