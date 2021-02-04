package com.ml.SalesApi.dao.implementation;

import com.ml.SalesApi.dao.IReceiptDAO;
import com.ml.SalesApi.dto.ReceiptDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReceiptDAO implements IReceiptDAO {
    private static List<ReceiptDTO> receipts = new ArrayList<>();
    @Override
    public ReceiptDTO addReceipt(ReceiptDTO newReceipt) {
        newReceipt.setId(receipts.size());
        receipts.add(newReceipt);
        return newReceipt;
    }

    @Override
    public List<ReceiptDTO> getReceiptsByEmail(String email) {
        List<ReceiptDTO> previousReceipts = new ArrayList<>();
        for (ReceiptDTO receipt:receipts) {
            if (receipt.getEmail().equals(email)) previousReceipts.add(receipt);
        }
        return previousReceipts;
    }
}
