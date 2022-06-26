package com.musinsa.pas.api.product;

import com.musinsa.pas.model.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImplA implements ProductService {

    @Autowired
    ProductDao productDao;

    /**
     * 상품 리스트 조회
     * @param product
     * @return
     */
    @Override
    public List<Map<String, Object>> getList(Product product) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Product> list = productDao.selectProduct(product);
        for (Product row : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("no", row.getNo());
            map.put("name", row.getName());
            map.put("content", row.getContent());
            map.put("regDate", row.getRegDate());
            map.put("modifyDate", row.getModifyDate());
            map.put("companyName", (row.getCompany() != null ?  row.getCompany().getCode() : ""));
            result.add(map);
        }
        return result;
    }

    /**
     * 상품 입력
     * @param product
     * @return
     */
    @Override
    public int insertProduct(Product product) {
        return productDao.insertProduct(product);
    }
}
