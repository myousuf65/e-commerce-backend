package dev.danvega.jwt.controller;

import dev.danvega.jwt.models.Product;
import dev.danvega.jwt.models.ResponseData;
import dev.danvega.jwt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }


    @PostMapping("/new")
    public ResponseData addProduct(
            @RequestParam("file")MultipartFile file,
            @RequestParam("name")String name,
            @RequestParam("desc")String desc,
            @RequestParam("category") String category,
            @RequestParam("price")float price,
            @RequestParam("username")String username
            ) throws Exception {

        Product product = productService.addProduct(file, name, desc, price, category, username);

        return new ResponseData(product.getProductName(), product.getFileType(), file.getSize());
    }

    @GetMapping("/download")
    public List<Product> getFiles(){
        return productService.getProducts();
    }

    @GetMapping("/findbycategory/{category}")
    public List<Product> getItemsbyCategory(@PathVariable("category") String category){
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/findbyid/{id}")
    public Optional<Product> getItemsbyId(@PathVariable("id") Long id){
        return productService.getProductsById(id);
    }

    @GetMapping("/findbyusername/{username}")
    public List<Product> getProductsByUsername(@PathVariable("username") String username){
        return productService.getProductsByUsername(username);
    }

    @GetMapping("/findbyname/{productname}")
    public Product getProductByProductName(@PathVariable("productname") String productname){
        return productService.getProductByProductName(productname);
    }

    @DeleteMapping("/deletebyid/{id}")
    public String deleteProductByName(@PathVariable("id") Long id){
        productService.deleteProductById(id);
        return "Deletion Successful";
    }

    @PutMapping("/update/{id}")
    public Product updateProductByName(
            @PathVariable("id") Long id,
            @RequestParam("name")String name,
            @RequestParam("desc")String desc,
            @RequestParam("price")float price
    ) throws Exception {
        return productService.updateProductById(id, name, desc, price);
    }


}
