package org.adk.shopping.product.rest;

import lombok.RequiredArgsConstructor;
import org.adk.shopping.product.dto.ProductDto;
import org.adk.shopping.product.dto.ProductRequest;
import org.adk.shopping.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok().body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable final String id) {
        return ResponseEntity
                .ok()
                .body(
                        productService.getById(id)
                                .orElseThrow(() -> new IllegalArgumentException("Not found resource for id: " + id))
                );
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody final ProductRequest productRequest) throws URISyntaxException {
        ProductDto productDto = productService.createProduct(productRequest);
        return ResponseEntity
                .created(new URI("/api/products/" + productDto.id()))
                .body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> edit(@RequestBody final ProductDto dto,
                                        @PathVariable final String id) {
        if (dto.id() == null) {
            throw new IllegalArgumentException("Invalid product id, null value");
        }
        if (!Objects.equals(dto.id(), id)) {
            throw new IllegalArgumentException("Invalid id");
        }

        return ResponseEntity
                .ok()
                .body(productService.edit(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final String id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
