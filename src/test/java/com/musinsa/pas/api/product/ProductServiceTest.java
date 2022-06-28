package com.musinsa.pas.api.product;

import com.musinsa.pas.model.dto.Company;
import com.musinsa.pas.model.dto.Product;
import com.musinsa.pas.model.dto.SelectAddData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void getListTest() {
        SelectAddData selectAddData = new SelectAddData();
        selectAddData.setOffset(1);
        selectAddData.setLimit(20);
        Product product = new Product();
        product.setSelectAddData(selectAddData);
        assertTrue(productService.getList(product).size() > 0);
    }

    @Test
    void insertProductTest() {
        Company company = new Company();
        company.setCode("EE115");
        Product product = new Product();
        product.setName("테스트 상품");
        product.setContent("테스트 상품 설명");
        product.setCompany(company);
        assertTrue(productService.insertProduct(product) > 0);
    }

}
