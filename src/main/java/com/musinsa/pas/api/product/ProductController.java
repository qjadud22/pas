package com.musinsa.pas.api.product;

import com.musinsa.pas.exception.HttpStatusException;
import com.musinsa.pas.model.dto.Product;
import com.musinsa.pas.result.PasStatus;
import com.musinsa.pas.result.ResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 상품 controller
 */
@RestController
public class ProductController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductService productService;

    @GetMapping(value = "v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultMessage> getProduct() {
        Product product = new Product();
        List<Map<String, Object>> list = productService.getList(product);
        logger.info("result : {}", list);
        /*
        if (0 < productService.getList(product).size()) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, PasStatus.PRE2001);
        }
*/
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(PasStatus.SUCCESS.getCode());
        resultMessage.setDesc(PasStatus.SUCCESS.getMessage());
        resultMessage.setResult(list);
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

    @PostMapping(value = "v1/products")
    public ResponseEntity<String> setProduct() {
        return new ResponseEntity<>("test22", HttpStatus.OK);
    }
}
