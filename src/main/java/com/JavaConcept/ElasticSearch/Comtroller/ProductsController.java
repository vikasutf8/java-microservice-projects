package com.JavaConcept.ElasticSearch.Comtroller;


import com.JavaConcept.ElasticSearch.Entity.Products;
import com.JavaConcept.ElasticSearch.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("all")
    public Iterable<Products> getProducts(){
        return  this.productService.getProducts();
    }

    @PostMapping("create")
    public Products createProduct(@RequestBody Products products){
        System.out.println(products);
        return  this.productService.createProduct(products);
    }
    @PutMapping("update/{Id}")
    public Products updateProduct(@RequestBody Products products,@PathVariable String Id){
        return  this.productService.updateProduct(products,Id);
    }
    @DeleteMapping("del/{Id}")
    public void deleteProduct(@PathVariable  String id){
        this.productService.deleteProduct(id);
    }

}
