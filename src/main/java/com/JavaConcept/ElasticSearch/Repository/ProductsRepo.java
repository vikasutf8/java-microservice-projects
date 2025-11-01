package com.JavaConcept.ElasticSearch.Repository;

import com.JavaConcept.ElasticSearch.Entity.Products;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductsRepo extends ElasticsearchRepository<Products,String> {


}
