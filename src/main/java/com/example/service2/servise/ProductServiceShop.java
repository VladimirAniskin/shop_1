package com.example.service2.servise;

import com.example.service2.dto.ProductDTO;
import com.example.service2.model.Product;
import com.example.service2.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceShop {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
        private ProductRepository productRepository;

    public ProductDTO getProductFromServiceWarehouse(Long productId) {
        return restTemplate.getForObject("http://localhost:8082/api/products/" + productId, ProductDTO.class);
    }

    public ProductDTO getProduct(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return new ProductDTO(product.getId(), product.getName(), product.getPrice());
        } else {
            return null;
        }
    }

}
