package com.musinsa.pas.api.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.pas.exception.HttpStatusException;
import com.musinsa.pas.model.dto.Company;
import com.musinsa.pas.model.dto.Product;
import com.musinsa.pas.model.dto.SelectAddData;
import com.musinsa.pas.result.PasStatus;
import com.musinsa.pas.result.ResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 상품 조회 API
     * @param no 상품 코드
     * @param offset offset
     * @param limit limit
     * @return
     */
    @GetMapping(value = "v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultMessage> getProduct(
            @RequestParam(value = "no", required = false, defaultValue = "0") int no,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "limit", required = false, defaultValue = "20") int limit
            ) {
        logger.info("code : {}", no);
        logger.info("offset : {}", offset);
        logger.info("limit : {}", limit);

        // 파라미터 유효성 오류
        if ((no < 0) || (offset < 0) || (limit >= 100)) {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, PasStatus.PRI2001);
        }

        SelectAddData selectAddData = new SelectAddData();
        selectAddData.setOffset(offset);
        selectAddData.setLimit(limit);

        Product product = new Product();
        product.setNo(no);
        product.setSelectAddData(selectAddData);

        // 상품 조회
        List<Map<String, Object>> list = productService.getList(product); // 상품 리스트 조회
        logger.info("result : {}", list);

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(PasStatus.SUCCESS.getCode());
        resultMessage.setDesc(PasStatus.SUCCESS.getMessage());
        resultMessage.setResult(list);
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

    /**
     * 상품 입력 API
     * @param request
     * name : 상품명
     * companyCode : 업체 아이디
     * content : 상품설명
     * @return
     */
    @PostMapping(value = "v1/products", produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResultMessage> setProduct(@RequestBody String request) {
        logger.info("request Body : {}", request);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(request);

            // 필수 값 누락
            if ((!node.has("name")
                    || (!node.has("companyCode"))
                    || (!node.has("content")))) {
                throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, PasStatus.PRE2002);
            }

            Company company = new Company();
            company.setCode(node.get("companyCode").asText());

            Product product = new Product();
            product.setContent(node.get("content").asText());
            product.setName(node.get("name").asText());
            product.setCompany(company);

            // 상품 입력
            productService.insertProduct(product);
        } catch (JsonMappingException e) {
            logger.error("setProduct JsonMappingException ", e);
            throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, PasStatus.PRE2002);
        } catch (JsonProcessingException e) {
            logger.error("setProduct JsonProcessingException ", e);
            throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, PasStatus.PRE2003);
        }

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setStatus(PasStatus.SUCCESS.getCode());
        resultMessage.setDesc(PasStatus.SUCCESS.getMessage());
        return new ResponseEntity<>(resultMessage, HttpStatus.CREATED);
    }
}
