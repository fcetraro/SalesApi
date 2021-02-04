package com.ml.SalesApi.service.implementation;

import com.ml.SalesApi.dao.IReceiptDAO;
import com.ml.SalesApi.dto.ArticleDTO;
import com.ml.SalesApi.dto.request.ArticlesDTO;
import com.ml.SalesApi.dto.request.ArticlesRequestDTO;
import com.ml.SalesApi.dto.request.PurchaseDTO;
import com.ml.SalesApi.dto.ReceiptDTO;
import com.ml.SalesApi.dto.response.ReceiptResponseDTO;
import com.ml.SalesApi.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IPurchaseServiceImplementation implements IPurchaseService {
    @Autowired
    private IReceiptDAO receipts;
    final String uri = "http://localhost:8080/";
    final String articlesEndpoint = "api/v1/articles/search";

    @Override
    public ReceiptResponseDTO purchaseProducts(PurchaseDTO purchase) {
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
        return null;
    }

    private String getRequestParams(List<ArticlesRequestDTO> articles){
        String requestParams = "";
        boolean first = true;
        for (ArticlesRequestDTO article:articles) {
            char separator  = '&';
            if(first){
                first=false;
                separator='?';
            }
            requestParams = requestParams+separator+"id="+article.getProductId();
        }
        return requestParams;
    }

    private List<ArticleDTO> getProducts(List<ArticlesRequestDTO> articles) {
        String getProductsUri = uri + articlesEndpoint + getRequestParams(articles);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(getProductsUri, ArticlesDTO.class).getArticles();
    }

    private ReceiptDTO generateReceipt(List<ArticleDTO> fullArticles, List<ArticlesRequestDTO> articles) {
        double total = 0;
        for (int i = 0; i < fullArticles.size(); i++) {
            total += calculateArticlePrice(fullArticles.get(i),articles.get(i));
        }
        ReceiptDTO receipt = new ReceiptDTO();
        receipt.setId(0);
        receipt.setStatus("PENDING");
        receipt.setTotal(total);
        receipt.setArticles(fullArticles);
        return receipt;
    }

    private double calculateArticlePrice(ArticleDTO articleDTO, ArticlesRequestDTO articlesRequestDTO) {
        double discountPerItem = articleDTO.getPrice() * articlesRequestDTO.getDiscount();
        return (articleDTO.getPrice() - discountPerItem) * articlesRequestDTO.getQuantity();
    }
}
