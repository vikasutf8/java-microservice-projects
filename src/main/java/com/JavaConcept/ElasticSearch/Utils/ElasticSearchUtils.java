package com.JavaConcept.ElasticSearch.Utils;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import java.util.function.Supplier;

public class ElasticSearchUtils {

    // Returns a Supplier that provides a match_all query when called
    public static Supplier<Query> supplier() {
        return () -> Query.of(q -> q.matchAll(matchAllQuery()));
    }

    // Builds and returns a MatchAllQuery object
    public static MatchAllQuery matchAllQuery() {
        return new MatchAllQuery.Builder().build();
    }



    // Returns a Supplier that provides a match_all query when called
    public static Supplier<Query> supplierField(String attributeValue ) {
        return () -> Query.of(q -> q.match(matchQueryField(attributeValue)));
    }

    // Builds and returns a MatchAllQuery object
    public static MatchQuery matchQueryField(String attributeValue) {
        return new MatchQuery.Builder().field("name").query(attributeValue).build();
    }

    // Returns a Supplier that provides a match_all query when called
    public static Supplier<Query> autoSuggestSupplierField(String partialAttributeValue ) {
        return () -> Query.of(q -> q.match(autoSuggestMatchQueryField(partialAttributeValue)));
    }

    // Builds and returns a MatchAllQuery object
    public static MatchQuery autoSuggestMatchQueryField(String partialAttributeValue) {
        return new MatchQuery.Builder().field("name").query(partialAttributeValue).analyzer("standard").build();
    }


}
//matchAllQuery() → creates { "match_all": {} }
//supplier() → provides a ready Query builder using that.
//        Used for fetching all documents in Elasticsearch easily.

