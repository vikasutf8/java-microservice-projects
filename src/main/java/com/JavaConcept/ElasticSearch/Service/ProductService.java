package com.JavaConcept.ElasticSearch.Service;

import com.JavaConcept.ElasticSearch.Entity.Products;
import com.JavaConcept.ElasticSearch.Repository.ProductsRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductsRepo productsRepo;


    public Iterable<Products> getProducts(){
        return  productsRepo.findAll();
    }

    public  Products createProduct(Products products){
        return productsRepo.save(products);
    }

    public  Products updateProduct(Products products,String id){
        Products existingProduct = productsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

//        existingProduct.setName(products.getName());
//        existingProduct.setDesc(products.getDesc());
//        existingProduct.setQuantity(products.getQuantity());
//        existingProduct.setPrice(products.getPrice());

        Products updatedProduct = productsRepo.save(existingProduct);

        return updatedProduct;
    }

    public void deleteProduct(String id){
         productsRepo.deleteById(id);
    }

}
