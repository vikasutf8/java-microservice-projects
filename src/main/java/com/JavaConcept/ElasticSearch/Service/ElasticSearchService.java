package com.JavaConcept.ElasticSearch.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.ccr.ElasticsearchCcrClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.JavaConcept.ElasticSearch.Entity.Products;
import com.JavaConcept.ElasticSearch.Utils.ElasticSearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;


    public SearchResponse<Map> matchAllService() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtils.supplier();

        SearchResponse<Map> searchResponse=elasticsearchClient.search(s->s.query(supplier.get()), Map.class);
        System.out.println("search resposne of elasticseach client"+supplier.get());
        return  searchResponse;

    }

    public SearchResponse<Products> matchAllProductService() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtils.supplier();
//indexs
        SearchResponse<Products> searchResponse=elasticsearchClient.search(s->s.index("products").query(supplier.get()), Products.class);
        System.out.println("search resposne of elasticseach client"+supplier.get());
        return  searchResponse;

    }

    public SearchResponse<Products> matchAllProductServiceWithFieldName(String attributeName) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtils.supplierField(attributeName);
//indexs
        SearchResponse<Products> searchResponse=elasticsearchClient.search(s->s.index("products").query(supplier.get()), Products.class);
        System.out.println("search resposne of elasticseach client"+supplier.get());
        return  searchResponse;

    }
}
