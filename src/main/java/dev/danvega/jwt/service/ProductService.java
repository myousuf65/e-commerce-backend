package dev.danvega.jwt.service;

import dev.danvega.jwt.models.Product;
import dev.danvega.jwt.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    Logger logger = LoggerFactory.getLogger(ProductService.class);


    @Autowired
    private ProductRepository repository;

    public Product addProduct(MultipartFile file, String name, String desc, float price, String category, String username) throws Exception {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

            Product product = new Product(file.getContentType(), name, desc, price, file.getBytes(), category, username);


            return repository.save(product);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return repository.findAllByProductCategory(category);
    }

    public Optional<Product> getProductsById(Long id) {
        return repository.findById(id);
    }

    public List<Product> getProductsByUsername(String username) {
        return repository.findAllByUsername(username);
    }

    public void deleteProductById(Long Id) {
        repository.deleteById(Id);
    }

    public Product getProductByProductName(String productname) {
        return repository.findByProductName(productname);
    }

    public Product updateProductById(Long id, Product product) {
        Product product1 = repository.findById(id).get();

        product1.setProductName(product.getProductName());
        product1.setProductDescription(product.getProductDescription());
        product1.setData(product.getData());
        product1.setProductCategory(product.getProductCategory());

        return repository.save(product1);
    }

    public Product updateProductById(Long id, String name, String desc, float price) throws IOException {

        Product product1 = repository.findById(id).get();
        product1.setProductName(name);
        product1.setProductDescription(desc);
        product1.setProductPrice(price);

        return repository.save(product1);
    }
}
