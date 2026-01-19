package org.ho.cqrs.product.query.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.ho.cqrs.common.dto.Pagination;

import java.util.List;

@Getter
@Builder
public class ProductListResponse {

  private final List<ProductDTO> products;
  private final Pagination pagination;

}
