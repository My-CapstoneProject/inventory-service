package com.sr.inventory_service.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProductPojo {
    private int productId;
    private int salesQuantity;
}
