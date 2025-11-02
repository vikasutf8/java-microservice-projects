package com.JavaConcept.ElasticSearch.Comtroller;


import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.JavaConcept.ElasticSearch.Entity.Products;
import com.JavaConcept.ElasticSearch.Service.ElasticSearchService;
import com.JavaConcept.ElasticSearch.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("all")
    public Iterable<Products> getProducts(){
        return  this.productService.getProducts();
    }
    //replacement of fetch all products elastic search instead of product service
    @GetMapping("searchProduct")
    public List<Products> searchProducts() throws IOException {
        SearchResponse<Products> searchResponse = this.elasticSearchService.matchAllProductService();
        List<Hit<Products>> hitsofsearchResponse =searchResponse.hits().hits();
        List<Products> productsList = hitsofsearchResponse.stream()
                .map(Hit::source)
                .filter(Objects::nonNull)
//                .filter(p -> Objects.nonNull(p.getId()))
                .collect(Collectors.toList());

        System.out.println(productsList);
        return productsList;
    }

    @GetMapping("searchProduct/{attributeName}")
    public List<Products> matchAllProductServiceWithFieldName(@PathVariable String attributeName) throws IOException {
        SearchResponse<Products> searchResponse = this.elasticSearchService.matchAllProductServiceWithFieldName(attributeName);
        List<Hit<Products>> hitsofsearchResponse =searchResponse.hits().hits();
        List<Products> productsList = hitsofsearchResponse.stream()
                .map(Hit::source)
                .filter(Objects::nonNull)
//                .filter(p -> Objects.nonNull(p.getId()))
                .collect(Collectors.toList());

        System.out.println(productsList);
        return productsList;
    }

    @GetMapping("matchAll")
    public SearchResponse<Map> searchMatchAll() throws IOException {
        SearchResponse<Map> searchResponse=  this.elasticSearchService.matchAllService();
        return searchResponse;
    //        return searchResponse.hits().hits().stream()
    //                .map(
    //                        hit->{
    //                            Map<String, Object> map =new HashMap<>();
    //                            map.put("_index", hit.index());
    //                            map.put("_id", hit.id());
    //                            return map;
    //                        }
    //                ) .collect(Collectors.toList());
    }



    @GetMapping("autoSuggestSearch/{partialAttributeName}")
    public List<String> autoSuggestSearchProduct(@PathVariable String partialAttributeName) throws IOException {
        SearchResponse<Products> searchResponse = this.elasticSearchService.autoSuggestMatchProducts(partialAttributeName);

        List<String> productsList = searchResponse.hits().hits().stream()
                .map(Hit::source)                          // extract _source (Products)
                .filter(Objects::nonNull)                   // skip nulls
                .map(Products::getName)                     // extract product name
                .filter(Objects::nonNull)                   // skip null names
                .toList();                                  // collect into List<String>

        System.out.println(productsList);
        return productsList;
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
