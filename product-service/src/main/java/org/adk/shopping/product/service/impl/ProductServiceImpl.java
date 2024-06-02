package org.adk.shopping.product.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.adk.shopping.product.document.Product;
import org.adk.shopping.product.dto.ProductDto;
import org.adk.shopping.product.dto.ProductRequest;
import org.adk.shopping.product.repository.ProductRepository;
import org.adk.shopping.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductDto createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product created successfully");
        return toDto(product);
    }

    @Override
    public ProductDto edit(ProductDto productDto) {
        Product product = productRepository.findById(productDto.id())
                .orElseThrow(() -> new IllegalArgumentException("Not found product for id: " + productDto.id()));
        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        return toDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Optional<ProductDto> getById(String id) {
        return productRepository.findById(id).map(this::toDto);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
