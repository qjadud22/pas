package com.musinsa.pas.api.product;

import com.musinsa.pas.model.dto.Product;
import java.util.List;

public interface ProductDao {

    /**
     * 상품 입력
     * @param product
     * @return
     */
    public int insertProduct(Product product);

    /**
     * 상품 조회
     * @param product
     * @return
     */
    public List<Product> selectProduct(Product product);
}
