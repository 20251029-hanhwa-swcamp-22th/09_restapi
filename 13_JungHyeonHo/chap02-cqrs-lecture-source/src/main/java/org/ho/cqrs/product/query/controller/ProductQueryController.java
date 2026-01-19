package org.ho.cqrs.product.query.controller;

import lombok.RequiredArgsConstructor;
import org.ho.cqrs.common.dto.ApiResponse;
import org.ho.cqrs.product.query.dto.request.ProductSearchRequest;
import org.ho.cqrs.product.query.dto.response.ProductDetailResponse;
import org.ho.cqrs.product.query.dto.response.ProductListResponse;
import org.ho.cqrs.product.query.service.ProductQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* 조회 담당 컨트롤러 (Query Side) */

@RestController
@RequiredArgsConstructor
public class ProductQueryController {

  private final ProductQueryService productQueryService;

  @GetMapping("/products/{productCode}")
  public ResponseEntity<ApiResponse<ProductDetailResponse>> getProduct(@PathVariable("productCode") Long productCode){
    ProductDetailResponse response = productQueryService.getProduct(productCode);

    return ResponseEntity.ok(ApiResponse.success(response));
  }

  /* 조건에 맞는 상품 목록 조회 */
  @GetMapping("/products")
  public ResponseEntity<ApiResponse<ProductListResponse>> getProducts(
      // @ModelAttribute (생략 가능)
      ProductSearchRequest productSearchRequest /* 커맨드 객체 */
  ){
    ProductListResponse response
        = productQueryService.getProducts(productSearchRequest);

    return ResponseEntity.ok(ApiResponse.success(response));
  }

}
