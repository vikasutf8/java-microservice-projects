# Elastic Search
- It is seach engine like google from db
- how data store:
  - In format of document like format  similer like mongoDb
  - Data is serialized in JSON format -- NOSQL database like key-value
- data aggregation --? both sql and nosql database parallel for filteration

- Properties
  - distrbuted
  - horizontal scale
  - restfull api centric

- Where we use ??
  - unstructured -semi-structured data

- concept
  - Inverse Indexing
  - eg:

- Termnology
  - node
  - Index
  - Mapping 
  - Document
  - shard


### Docker
- docker pull
```
docker pull docker.elastic.co/elasticsearch/elasticsearch:8.13.4
```

- docker run 
```
docker run -d \
--name elasticsearch \
-p 9200:9200 \
-e discovery.type=single-node \
-e xpack.security.enabled=false \
docker.elastic.co/elasticsearch/elasticsearch:8.13.4 
```

- Explanation:
    - -d → run in background
    - --name elasticsearch → container name
    - -p 9200:9200 → expose HTTP API port
    - discovery.type=single-node → for local setup
    - xpack.security.enabled=false → disables auth (good for local dev)
    - ES_JAVA_OPTS → sets heap size

```
➜  ElasticSearch:  curl http://localhost:9200

{
"name" : "7b60399e32cc",
"cluster_name" : "docker-cluster",
"cluster_uuid" : "IE35fQ8dTOe02ucvjzAiTg",
"version" : {
"number" : "8.13.4",
"build_flavor" : "default",
"build_type" : "docker",
"build_hash" : "da95df118650b55a500dcc181889ac35c6d8da7c",
"build_date" : "2024-05-06T22:04:45.107454559Z",
"build_snapshot" : false,
"lucene_version" : "9.10.0",
"minimum_wire_compatibility_version" : "7.17.0",
"minimum_index_compatibility_version" : "7.0.0"
},
"tagline" : "You Know, for Search"
}
```


---
## API Doc
- Index create
- ElasticSearch ; curl http://localhost:9200/products
- [products] -index create on elastic search
- This JSON means your Elasticsearch index “products” is successfully created,
  and currently only contains a default _class mapping (no custom fields yet — they’ll appear once you save a Products document).
```json

{
  "products": {
    "aliases": {},
    "mappings": {
      "properties": {
        "_class": {
          "type": "keyword",
          "index": false,
          "doc_values": false
        }
      }
    },
    "settings": {
      "index": {
        "routing": {
          "allocation": {
            "include": {
              "_tier_preference": "data_content"
            }
          }
        },
        "refresh_interval": "1s",
        "number_of_shards": "1",
        "provided_name": "products",
        "creation_date": "1761978910124",
        "number_of_replicas": "1",
        "uuid": "DCx6F5SDR6ufw40F36wtyg",
        "version": {
          "created": "8503000"
        }
      }
    }
  }
}
```

- find all product
- GET localhost:8080/api/v1/products/all
  - content contain all data of product --rest are elastic seach configuration
```json
{
    "content": [], 
    "pageable": "INSTANCE",
    "totalPages": 1,
    "totalElements": 0,
    "last": true,
    "size": 0,
    "number": 0,
    "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
    },
    "numberOfElements": 0,
    "first": true,
    "empty": true
}
```
- databases query of elastic serach
- GET http://localhost:9200/products/_search 

```json
{
    "took": 43,
    "timed_out": false,
    "_shards": {
        "total": 1,
        "successful": 1,
        "skipped": 0,
        "failed": 0
    },
    "hits": {
        "total": {
            "value": 7,
            "relation": "eq"
        },
        "max_score": 1.0,
        "hits": [
            {
                "_index": "products",
                "_id": "LttnPpoBM-WsOBBSS1Jv",
                "_score": 1.0,
                "_source": {
                    "_class": "com.JavaConcept.ElasticSearch.Entity.Products",
                    "name": "iPhone 15 Pro",
                    "desc": "Titanium body, A17 Pro chip",
                    "quantity": 30,
                    "price": 149999.99
                }
            }
        ]
    }
}
```
- now filtering them - based onn query
- Query
- match -whatever field inside it
```json
{
    "query":{
        "match":{
            "id":534,
          "name":"ip*"
        }
    }
}
```
---
- IMPORTANT
- Rule of thumb:
   - Use @PathVariable for resource identification (/user/5) - findbyid
   - Use @RequestParam for filters or optional parameters (/user?active=true)  seach

- In elastic search HIT - added addition attributed in oure entityu response so handle that..
  - @JsonIgnoreProperties(ignoreUnknown = true)

---

### Query DSL(domain specific languanges)
- MatchAllQuery →
  - This is an Elasticsearch query that matches all documents in an index.
  - Equivalent to SQL:SELECT * FROM products;

- GET http://localhost:8080/api/v1/products/matchAll  
- search resposne of elasticseach clientQuery: {"match_all":{}}
- Get http://localhost:8080/api/v1/products/searchProduct
- Get http://localhost:8080/api/v1/products/searchProduct/iphone