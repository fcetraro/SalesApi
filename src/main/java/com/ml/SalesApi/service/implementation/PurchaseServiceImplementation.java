package com.ml.SalesApi.service.implementation;

import com.ml.SalesApi.communicator.IProductApiCommunicator;
import com.ml.SalesApi.communicator.implementation.ProductApiCommunicator;
import com.ml.SalesApi.dao.IReceiptDAO;
import com.ml.SalesApi.dto.ArticleDTO;
import com.ml.SalesApi.dto.request.*;
import com.ml.SalesApi.dto.ReceiptDTO;
import com.ml.SalesApi.dto.response.PurchaseResponseDTO;
import com.ml.SalesApi.dto.response.ReceiptResponseDTO;
import com.ml.SalesApi.dto.response.StatusCodeDTO;
import com.ml.SalesApi.exception.concreteExceptions.ConnectionException;
import com.ml.SalesApi.exception.concreteExceptions.NoStockException;
import com.ml.SalesApi.exception.concreteExceptions.ProductNotFoundException;
import com.ml.SalesApi.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImplementation implements IPurchaseService {
    @Autowired
    private IReceiptDAO receipts;

    private IProductApiCommunicator productApi;

    @Autowired
    public PurchaseServiceImplementation(IProductApiCommunicator implementation){
        productApi=implementation;
    }

    @Override
    public PurchaseResponseDTO purchaseProducts(PurchaseDTO purchase) {
        StatusCodeDTO newStatusCode = new StatusCodeDTO();
        newStatusCode.setCode(200);
        newStatusCode.setMessage("La solicitud se completo con exito.");
        PurchaseResponseDTO response = new PurchaseResponseDTO();
        response.setReceipt(purchase(purchase));
        response.setStatusCode(newStatusCode);
        return response;
    }

    private ReceiptResponseDTO purchase(PurchaseDTO purchase) {
        List<ArticleDTO> articles = getProducts(purchase.getArticles());
        if(articles.size()==purchase.getArticles().size()){
            ReceiptDTO toAddReceipt = generateReceipt(articles,purchase.getArticles());
            toAddReceipt.setEmail(purchase.getUserName());
            ReceiptDTO newReceipt = receipts.addReceipt(toAddReceipt);
            double total=0;
            List<ReceiptDTO> previousReceipts = receipts.getReceiptsByEmail(purchase.getUserName());
            for (ReceiptDTO previousReceipt:previousReceipts) {
                total += previousReceipt.getTotal();
            }
            return new ReceiptResponseDTO(newReceipt, total, previousReceipts);
        }
        throw new ProductNotFoundException("Producto que desea comprar no existe", new Exception());
    }

    public List<ArticleDTO> getProducts(List<ArticlesRequestDTO> articles) {
        return productApi.getProducts(articles);
    }

    private void modifyProducts(List<QuantityArticleDTO> articles) {
        productApi.modifyProducts(articles);
    }

    private ReceiptDTO generateReceipt(List<ArticleDTO> fullArticles, List<ArticlesRequestDTO> articles) {
        double total = 0;
        List<QuantityArticleDTO> buyedArticles = new ArrayList<>();
        for (int i = 0; i < fullArticles.size(); i++) {
            total += calculateArticlePrice(fullArticles.get(i),articles.get(i));
            if(fullArticles.get(i).getQuantity()<articles.get(i).getQuantity()){
                throw new NoStockException("No hay stock del producto "+fullArticles.get(i).getName(), new Exception());
            }
            QuantityArticleDTO toModify = new QuantityArticleDTO();
            toModify.setQuantityBought(articles.get(i).getQuantity());
            toModify.setId(articles.get(i).getProductId());
            buyedArticles.add(toModify);
        }
        modifyProducts(buyedArticles);
        ReceiptDTO receipt = new ReceiptDTO();
        receipt.setId(0);
        receipt.setStatus("PENDING");
        receipt.setTotal(total);
        receipt.setArticles(fullArticles);
        return receipt;
    }

    private double calculateArticlePrice(ArticleDTO articleDTO, ArticlesRequestDTO articlesRequestDTO) {
        double discountPerItem = articleDTO.getPrice() * articlesRequestDTO.getDiscount() / 100;
        return (articleDTO.getPrice() - discountPerItem) * articlesRequestDTO.getQuantity();
    }
}
