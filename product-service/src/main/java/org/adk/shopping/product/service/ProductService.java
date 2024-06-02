package org.adk.shopping.product.service;

import org.adk.shopping.product.document.Product;
import org.adk.shopping.product.dto.ProductDto;
import org.adk.shopping.product.dto.ProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDto createProduct(ProductRequest productRequest);
    ProductDto edit(ProductDto productDto);
    List<ProductDto> findAll();
    Optional<ProductDto> getById(String id);
    void delete(String id);
    default ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
