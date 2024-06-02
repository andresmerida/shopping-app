package org.adk.shopping.product.repository;

import org.adk.shopping.product.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
