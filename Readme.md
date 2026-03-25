# PayPal - Payment System

## User-Service
-  Spring web -> RESTfull API client and server request and response
-  Spring JPA -> java object/entity relational mapping with database
-  h2 database -> in memory database

-  Spring Security -> authentication and authorization
![alt text](image.png)
#### Questions
1. what is entity? 
- A table in database stored as an object name in Table annotation
2. ResponseEntity -> what is it?
- ResponseEntity is a class that represents a response from a RESTful API as json format


### API Restfull Documations

---

## API-Gateway


---
## Payment Service


---
## Notification Service


---
## Wallet Service


---
## Reward Service

---
# Docker ==Compose. -- 
-  kafka 
   docker-compose up -d
-  zookeeper

```
➜  paypal-clone git:(paypal) ✗ docker ps            
CONTAINER ID   IMAGE                             COMMAND                  CREATED          STATUS          PORTS                                        NAMES
31aba80914a5   confluentinc/cp-kafka:7.4.1       "/etc/confluent/dock…"   20 seconds ago   Up 18 seconds   0.0.0.0:9092->9092/tcp                       paypal-clone-kafka-1
c8cc6bd700a7   confluentinc/cp-zookeeper:7.4.1   "/etc/confluent/dock…"   20 seconds ago   Up 19 seconds   2888/tcp, 0.0.0.0:2181->2181/tcp, 3888/tcp   paypal-clone-zookeeper-1

```