package com.musinsa.pas.api.product;

import com.musinsa.pas.model.dto.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    /**
     * 상품 조회
     * @param product
     * @return
     */
    public List<Map<String, Object>> getList(Product product);

    /**
     * 상품 입력
     * @param product
     * @return
     */
    public int insertProduct(Product product);
}
