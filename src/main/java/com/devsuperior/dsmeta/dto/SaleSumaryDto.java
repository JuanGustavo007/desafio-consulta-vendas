package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleSumaryDto {

    private String sellerName;
    private Double amount;

    public SaleSumaryDto(String sellerName, Double amount) {
        this.sellerName = sellerName;
        this.amount = amount;
    }

    public SaleSumaryDto(Sale entity) {
        sellerName = entity.getSeller().getName();
        amount = entity.getAmount();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getAmount() {
        return amount;
    }
}
